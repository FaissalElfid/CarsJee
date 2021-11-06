/**
 *  Copyright 2009-2020 PrimeTek.
 *
 *  Licensed under PrimeFaces Commercial License, Version 1.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  Licensed under PrimeFaces Commercial License, Version 1.0 (the "License");
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.primefaces.freya.component;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.faces.FacesException;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import org.primefaces.component.api.AjaxSource;
import org.primefaces.component.api.UIOutcomeTarget;
import org.primefaces.component.menu.AbstractMenu;
import org.primefaces.component.menu.BaseMenuRenderer;
import org.primefaces.expression.SearchExpressionFacade;
import org.primefaces.model.menu.MenuElement;
import org.primefaces.model.menu.MenuItem;
import org.primefaces.model.menu.Separator;
import org.primefaces.model.menu.Submenu;
import org.primefaces.util.AjaxRequestBuilder;
import org.primefaces.util.ComponentTraversalUtils;
import org.primefaces.util.WidgetBuilder;

public class FreyaMenuRenderer extends BaseMenuRenderer {

    @Override
    protected void encodeMarkup(FacesContext context, AbstractMenu abstractMenu) throws IOException {
        FreyaMenu menu = (FreyaMenu) abstractMenu;
        ResponseWriter writer = context.getResponseWriter();
        String style = menu.getStyle();
        String styleClass = menu.getStyleClass();
        String defaultStyleClass = "layout-menu";
        styleClass = styleClass == null ? defaultStyleClass : defaultStyleClass + " " + styleClass;
        
        writer.startElement("ul", menu);  
        writer.writeAttribute("id", menu.getClientId(context), "id");
        writer.writeAttribute("class", styleClass, "styleClass");
        
        if(style != null) {
            writer.writeAttribute("style", style, "style");
        }
        
        if(menu.getElementsCount() > 0) {
            encodeElements(context, menu, menu.getElements());
        }
        
        writer.endElement("ul");
    }
    
    protected void encodeElements(FacesContext context, AbstractMenu menu, List<MenuElement> elements) throws IOException {
        int size = elements.size();
        
        for (int i = 0; i < size; i++) {
            encodeElement(context, menu, elements.get(i));
        }
    }
    
    protected void encodeElement(FacesContext context, AbstractMenu menu, MenuElement element) throws IOException {
        ResponseWriter writer = context.getResponseWriter();
        
        if(element.isRendered()) {
            if(element instanceof MenuItem) {
                MenuItem menuItem = (MenuItem) element;
                String menuItemClientId = (menuItem instanceof UIComponent) ? menuItem.getClientId() : menu.getClientId(context) + "_" + menuItem.getClientId();
                String containerStyle = menuItem.getContainerStyle();
                String containerStyleClass = menuItem.getContainerStyleClass();

                writer.startElement("li", null);
                writer.writeAttribute("id", menuItemClientId, null);
                writer.writeAttribute("role", "menuitem", null);
                
                if(containerStyle != null) writer.writeAttribute("style", containerStyle, null);
                if(containerStyleClass != null) writer.writeAttribute("class", containerStyleClass, null);

                encodeMenuItem(context, menu, menuItem);

                writer.endElement("li");
            }
            else if(element instanceof Submenu) {
                Submenu submenu = (Submenu) element;
                String submenuClientId = (submenu instanceof UIComponent) ? ((UIComponent) submenu).getClientId() : menu.getClientId(context) + "_" + submenu.getId();
                String style = submenu.getStyle();
                String styleClass = submenu.getStyleClass();
                boolean expanded = submenu.isExpanded();
                styleClass = styleClass != null ? styleClass : "";
                styleClass = expanded ? "active-menuitem " + styleClass : styleClass;
                
                writer.startElement("li", null);
                writer.writeAttribute("id", submenuClientId, null);
                writer.writeAttribute("role", "menuitem", null);
                
                if(style != null) writer.writeAttribute("style", style, null);
                if(styleClass != null) writer.writeAttribute("class", styleClass, null);

                encodeSubmenu(context, menu, submenu);

                writer.endElement("li");
            }
            else if(element instanceof Separator) {
                encodeSeparator(context, (Separator) element);
            }
        }
    }
    
    protected void encodeSubmenu(FacesContext context, AbstractMenu menu, Submenu submenu) throws IOException{
		ResponseWriter writer = context.getResponseWriter();
        String icon = submenu.getIcon();
        String label = submenu.getLabel();
        int childrenElementsCount = submenu.getElementsCount();
        boolean expanded = submenu.isExpanded();
        
        writer.startElement("a", null);
        writer.writeAttribute("href", "#", null);
        
        encodeItemIcon(context, icon);

        if(label != null) {          
            writer.startElement("span", null);
            writer.writeText(label, null);
            writer.endElement("span");  
            
            encodeToggleIcon(context, submenu, childrenElementsCount);
        }
               
        writer.endElement("a");
        
        if(label != null) {
            encodeTooltip(context, label);
        }

        //submenus and menuitems
        if(childrenElementsCount > 0) {
            writer.startElement("ul", null);
            writer.writeAttribute("role", "menu", null);
            if(expanded) writer.writeAttribute("style", "display:block", null);
			encodeElements(context, menu, submenu.getElements());
			writer.endElement("ul");
        }
	}
    
    protected void encodeItemIcon(FacesContext context, String icon) throws IOException {
        if(icon != null) {
            ResponseWriter writer = context.getResponseWriter();

            writer.startElement("i", null);
            if(icon.contains("fa ")) 
                icon += " fa-fw";

            writer.writeAttribute("class", icon, null);
            writer.endElement("i");
        }
    }
    
    protected void encodeToggleIcon(FacesContext context, Submenu submenu, int childrenElementsCount) throws IOException {
        if(childrenElementsCount > 0) {
            ResponseWriter writer = context.getResponseWriter();
            
            writer.startElement("i", null);
            writer.writeAttribute("class", "pi pi-chevron-down layout-submenu-toggler  ", null);
            writer.endElement("i");
        }
    }

    @Override
    protected void encodeSeparator(FacesContext context, Separator separator) throws IOException {
        ResponseWriter writer = context.getResponseWriter();
        String style = separator.getStyle();
        String styleClass = separator.getStyleClass();
        styleClass = styleClass == null ? "Separator" : "Separator " + styleClass;

        //title
        writer.startElement("li", null);
        writer.writeAttribute("class", styleClass, null);
        if(style != null) {
            writer.writeAttribute("style", style, null);
        }
        
        writer.endElement("li");
    }
    
    @Override
    protected void encodeMenuItem(FacesContext context, AbstractMenu menu, MenuItem menuitem) throws IOException {
        ResponseWriter writer = context.getResponseWriter();
        String title = menuitem.getTitle();
        boolean disabled = menuitem.isDisabled();
        Object value = menuitem.getValue();
        String style = menuitem.getStyle();
        String styleClass = menuitem.getStyleClass();

        writer.startElement("a", null);
        if(title != null) writer.writeAttribute("title", title, null);
        if(style != null) writer.writeAttribute("style", style, null);
        if(styleClass != null) writer.writeAttribute("class", styleClass, null);

        if(disabled) {
            writer.writeAttribute("href", "#", null);
            writer.writeAttribute("onclick", "return false;", null);
        }
        else {
            String onclick = menuitem.getOnclick();

            //GET
            if(menuitem.getUrl() != null || menuitem.getOutcome() != null) {                
                String targetURL = getTargetURL(context, (UIOutcomeTarget) menuitem);
                writer.writeAttribute("href", targetURL, null);

                if(menuitem.getTarget() != null) {
                    writer.writeAttribute("target", menuitem.getTarget(), null);
                }
            }
            //POST
            else {
                writer.writeAttribute("href", "#", null);

                UIComponent form = ComponentTraversalUtils.closestForm(context, menu);
                if(form == null) {
                    throw new FacesException("MenuItem must be inside a form element");
                }

                String command;
                if(menuitem.isDynamic()) {
                    String menuClientId = menu.getClientId(context);
                    Map<String,List<String>> params = menuitem.getParams();
                    if(params == null) {
                        params = new LinkedHashMap<String, List<String>>();
                    }
                    List<String> idParams = new ArrayList<String>();
                    idParams.add(menuitem.getId());
                    params.put(menuClientId + "_menuid", idParams);

                    command = menuitem.isAjax() ? createAjaxRequest(context, menu, (AjaxSource) menuitem, form, params) : buildNonAjaxRequest(context, menu, form, menuClientId, params, true);
                } 
                else {
                    command = menuitem.isAjax() ? createAjaxRequest(context, (AjaxSource) menuitem, form) : buildNonAjaxRequest(context, ((UIComponent) menuitem), form, ((UIComponent) menuitem).getClientId(context), true);
                }

                onclick = (onclick == null) ? command : onclick + ";" + command;
            }

            if(onclick != null) {
                writer.writeAttribute("onclick", onclick, null);
            }
        }

        encodeMenuItemContent(context, menu, menuitem);

        writer.endElement("a");
        
        if(value != null) {
            encodeTooltip(context, value);
        }
	}
    
    protected void encodeTooltip(FacesContext context, Object value) throws IOException {
        ResponseWriter writer = context.getResponseWriter();
        
        writer.startElement("div", null);
            writer.writeAttribute("class", "layout-menu-tooltip", null);
            writer.startElement("div", null);
            writer.writeAttribute("class", "layout-menu-tooltip-arrow", null);
            writer.endElement("div");
            writer.startElement("div", null);
            writer.writeAttribute("class", "layout-menu-tooltip-text", null);
            writer.writeText(value, null);
            writer.endElement("div");
        writer.endElement("div");
    }
    
    @Override
    protected void encodeMenuItemContent(FacesContext context, AbstractMenu menu, MenuItem menuitem) throws IOException {
        ResponseWriter writer = context.getResponseWriter();
        String icon = menuitem.getIcon();
        Object value = menuitem.getValue();
                
        encodeItemIcon(context, icon);

        if(value != null) {
            writer.startElement("span", null);
            writer.writeText(value, "value");
            writer.endElement("span");
        }
    }

    @Override
    protected void encodeScript(FacesContext context, AbstractMenu abstractMenu) throws IOException {
        FreyaMenu menu = (FreyaMenu) abstractMenu;
        String clientId = menu.getClientId(context);
        WidgetBuilder wb = getWidgetBuilder(context);
        wb.init("Freya", menu.resolveWidgetVar(), clientId)
            .attr("closeDelay", Integer.toString(menu.getCloseDelay()));

        wb.finish();
    }
    
    protected String createAjaxRequest(FacesContext context, AjaxSource source, UIComponent form) {
        UIComponent component = (UIComponent) source;
        String clientId = component.getClientId(context);
        
        AjaxRequestBuilder builder = getAjaxRequestBuilder();

        builder.init()
                .source(clientId)
                .form(SearchExpressionFacade.resolveClientId(context, component, source.getForm()))
                .process(component, source.getProcess())
                .update(component, source.getUpdate())
                .async(source.isAsync())
                .global(source.isGlobal())
                .delay(source.getDelay())
                .timeout(source.getTimeout())
                .partialSubmit(source.isPartialSubmit(), source.isPartialSubmitSet(), source.getPartialSubmitFilter())
                .resetValues(source.isResetValues(), source.isResetValuesSet())
                .ignoreAutoUpdate(source.isIgnoreAutoUpdate())
                .onstart(source.getOnstart())
                .onerror(source.getOnerror())
                .onsuccess(source.getOnsuccess())
                .oncomplete(source.getOncomplete())
                .params(component);

        if (form != null) {
            builder.form(form.getClientId(context));
        }

        builder.preventDefault();

        return builder.build();
    }
    
    protected String createAjaxRequest(FacesContext context, AbstractMenu menu, AjaxSource source, UIComponent form,
            Map<String, List<String>> params) {
        
        String clientId = menu.getClientId(context);

        AjaxRequestBuilder builder = getAjaxRequestBuilder();

        builder.init()
                .source(clientId)
                .process(menu, source.getProcess())
                .update(menu, source.getUpdate())
                .async(source.isAsync())
                .global(source.isGlobal())
                .delay(source.getDelay())
                .timeout(source.getTimeout())
                .partialSubmit(source.isPartialSubmit(), source.isPartialSubmitSet(), source.getPartialSubmitFilter())
                .resetValues(source.isResetValues(), source.isResetValuesSet())
                .ignoreAutoUpdate(source.isIgnoreAutoUpdate())
                .onstart(source.getOnstart())
                .onerror(source.getOnerror())
                .onsuccess(source.getOnsuccess())
                .oncomplete(source.getOncomplete())
                .params(params);

        if (form != null) {
            builder.form(form.getClientId(context));
        }

        builder.preventDefault();

        return builder.build();
    }
    
    protected AjaxRequestBuilder getAjaxRequestBuilder() {
        Class rootContext;
        Object requestContextInstance;
        AjaxRequestBuilder builder;

        try {
            rootContext = Class.forName("org.primefaces.context.PrimeRequestContext");
        } catch (ClassNotFoundException ex) {
            try {
                rootContext = Class.forName("org.primefaces.context.RequestContext");
            } catch (ClassNotFoundException ex1) {
                throw new RuntimeException(ex1);
            }
        }

        try {
            Method method = rootContext.getMethod("getCurrentInstance");
            requestContextInstance = method.invoke(null);

            method = requestContextInstance.getClass().getMethod("getAjaxRequestBuilder");
            builder = (AjaxRequestBuilder) method.invoke(requestContextInstance);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

        return builder;
    }
}
