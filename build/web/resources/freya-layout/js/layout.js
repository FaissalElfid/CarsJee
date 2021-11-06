/** 
 * PrimeFaces Freya Layout
 */
PrimeFaces.widget.Freya = PrimeFaces.widget.BaseWidget.extend({

    init: function(cfg) {
        this._super(cfg);
        this.wrapper = $(document.body).children('.layout-wrapper');
        var $this = this;

        $(function() {
            $this._init();
        });

        this.restoreMenuState();
        this.expandedMenuitems = this.expandedMenuitems||[];
    },

    _init: function() {
        this.contentWrapper = this.wrapper.children('.layout-main');
        this.topbar = this.wrapper.find('.layout-topbar');
        this.topbarItems = this.topbar.find('.layout-topbar-actions > li.topbar-item');
        this.topbarLinks = this.topbarItems.children('a');
        this.topbarSearchItemMenu = this.topbar.find('.search-item');
        
        this.menuWrapper = this.wrapper.find('.menu-wrapper');
        this.sidebarPin = this.menuWrapper.find('.sidebar-logo > .sidebar-pin');
        this.menu = this.menuWrapper.find('.layout-menu');
        this.menuButton = this.topbar.find('.menu-button');
        this.menulinks = this.menu.find('a');

        this.rightpanel = this.wrapper.find('.layout-rightpanel');
        this.rightpanelButton = this.topbar.find('.layout-rightpanel-button');
        this.rightpanelExitButton = this.rightpanel.find('.rightpanel-exit-button');

        this.configButton = $('#layout-config-button');
        this.configMenu = $('#layout-config');
        this.configMenuClose = this.configMenu.find('> .layout-config-content > .layout-config-close');

        this.bindEvents();
    },

    toggleClass: function(el, className) {
        if (el.hasClass(className)) {
            el.removeClass(className);
        }
        else {
            el.addClass(className);
        }
    },
    
    bindEvents: function() {
        var $this = this;

        this.bindTopbarEvents();
        this.bindMenuEvents();
        this.bindRightPanelEvents();
        this.bindConfigEvents();

        $(document.body).off('click.layoutBody').on('click.layoutBody', function() {
            if (!$this.menuClick) {
                $this.wrapper.removeClass('layout-sidebar-active layout-mobile-active');
                $(document.body).removeClass('blocked-scroll');

                if ($this.isHorizontal() || $this.isSlim()) {
                    $this.menu.find('.active-menuitem').removeClass('active-menuitem');
                    $this.menu.find('ul:visible').hide();
                    $this.menuActive = false;
                } 
            }

            if (!$this.topbarItemClicked) {
                $this.removeTopbarClassFromAllItems(null, 'active-topmenuitem', $this.topbarItems.filter('.active-topmenuitem'));
            }

            if (!$this.rightpanelClicked) {
                $this.wrapper.removeClass('layout-rightpanel-active');
            }

            if (!$this.configMenuClicked && $this.configMenu.hasClass('layout-config-active')) {
                if (!$this.wrapper.hasClass('layout-mobile-active') && !$this.wrapper.hasClass('layout-overlay-active')) {
                    $(document.body).removeClass('blocked-scroll');
                }
                $this.configMenu.removeClass('layout-config-active');
            }

            $this.horizontalMenuClick = false;
            $this.topbarItemClicked = false;
            $this.rightpanelClicked = false;
            $this.menuClick = false;
            $this.configMenuClicked = false;
        });
    },

    bindConfigEvents: function() {
        var $this = this;
        
        this.configButton.off('click.config').on('click.config', function(e) {
            $this.configMenuClicked = true;

            if ($this.configMenu.hasClass('layout-config-active')) {
                $this.configMenu.removeClass('layout-config-active');
                $(document.body).removeClass('blocked-scroll');
            }
            else {
                $this.configMenu.addClass('layout-config-active');
                $(document.body).addClass('blocked-scroll');
            }
            
            e.preventDefault();
        });

        this.configMenuClose.off('click.config').on('click.config', function(e) {
            $this.configMenu.removeClass('layout-config-active');
            $this.configMenuClicked = true;
            $(document.body).removeClass('blocked-scroll');
            e.preventDefault();
        });
        
        this.configMenu.off('click.configMenu').on('click.configMenu', function() {
            $this.configMenuClicked = true;
        });
    },

    bindMenuEvents: function() {
        var $this = this;

        this.menuButton.off('click.menu').on('click.menu', function(e) {
            $this.menuClick = true;

            if ($this.isMobile()) {
                if ($this.wrapper.hasClass('layout-mobile-active')) {
                    $this.wrapper.removeClass('layout-mobile-active');
                    $(document.body).removeClass('blocked-scroll');
                }
                else {
                    $this.wrapper.addClass('layout-mobile-active');
                    $(document.body).addClass('blocked-scroll');
                }
            }

            e.preventDefault();
        });

        this.menuWrapper.off('click.menuWrapper mouseenter.menuWrapper mouseleave.menuWrapper')
        .on('click.menuWrapper', function() {
            $this.menuClick = true;
        })
        .on('mouseenter.menuWrapper', function(e) {
            if(!$this.wrapper.hasClass('layout-sidebar-static')) {
                if($this.hideTimeout) {
                    clearTimeout($this.hideTimeout);
                }
                
                $this.menuWrapper.addClass('layout-sidebar-active');
            }
            if(!$this.wrapper.hasClass('layout-sidebar')) {
                if($this.hideTimeout) {
                    clearTimeout($this.hideTimeout);
                }
                
                $this.menuWrapper.removeClass('layout-sidebar-active');
            }
        })
        .on('mouseleave.menuWrapper', function(e) {
            if(!$this.wrapper.hasClass('layout-sidebar-static')) {
                $this.hideTimeout = setTimeout(function() {
                    $this.menuWrapper.removeClass('layout-sidebar-active');
                }, $this.cfg.closeDelay); 
            }
        });

        this.sidebarPin.off('click.menuWrapper').on('click.menuWrapper', function(e) {
            $this.wrapper.removeClass('layout-static-restore');
            $this.wrapper.toggleClass('layout-static');
            $this.saveMenuState();
            e.preventDefault();
        });
    
        this.menulinks.off('click.menuWrapper').on('click.menuWrapper', function(e) {
            var link = $(this),
            item = link.parent(),
            submenu = item.children('ul');
            horizontal = $this.isHorizontal();
            slim = $this.isSlim();
            $this.menuClick = true;

            if (horizontal) {
                $this.horizontalMenuClick = true;
            }

            if(item.hasClass('active-menuitem')) {
                if(submenu.length) {
                    $this.removeMenuitem(item.attr('id'));
                    item.removeClass('active-menuitem');
                    
                    if(horizontal || slim) {
                        if(item.parent().is($this.jq)) {
                            $this.menuActive = false;
                        }
                        
                        submenu.hide();
                        $this.removeMenuitem(item.attr('id'));
                        item.removeClass('active-menuitem');
                    }
                    else {
                        submenu.slideUp(function() {
                            $this.removeMenuitem(item.attr('id'));
                            item.removeClass('active-menuitem');
                        });
                    }
                }
            }
            else {
                $this.addMenuitem(item.attr('id'));

                if(horizontal || slim) {
                    $this.deactivateItems(item.siblings());
                    item.addClass('active-menuitem');
                    $this.menuActive = true;
                    submenu.show();
                }
                else {
                    $this.deactivateItems(item.siblings(), true);
                    $this.activate(item);
                }
            }
                                                
            if(submenu.length) {
                e.preventDefault();
            }
        });
        
        this.menu.find('> li').off('mouseenter.menu').on('mouseenter.menu', function(e) {    
            if ($this.isHorizontal() || $this.isSlim()) {
                var item = $(this);
                
                if(!item.hasClass('active-menuitem')) {
                    $this.menu.find('.active-menuitem').removeClass('active-menuitem');
                    $this.menu.find('ul:visible').hide();
                    
                    if($this.menuActive) {
                        item.addClass('active-menuitem');
                        item.children('ul').show();
                    }
                }
            }
        });
    },
    
    bindTopbarEvents: function() {
        var $this = this;

        this.topbarLinks.off('click.topbar').on('click.topbar', function(e) {
            var link = $(this),
            item = link.parent(),
            submenu = item.children('ul');

            if ($this.isMobile()) {
                $this.removeTopbarClassFromAllItems(null, 'active-topmenuitem', $this.topbarItems.filter('.active-topmenuitem').not(item));
            }
            else {
                $this.removeTopbarClassFromAllItems(item, 'active-topmenuitem');
            }
            $this.addTopbarClass(item, 'active-topmenuitem');

            $this.topbarItemClicked = true;
            
            if (submenu.length) {
                e.preventDefault();
            }
        });

        this.topbarSearchItemMenu.off('click.topbar').on('click.topbar', function(e) {
            $this.topbarItemClicked = true;
        });
    },

    bindRightPanelEvents: function() {
        var $this = this;
        var changeRightpanelState = function(e) {
            this.toggleClass(this.wrapper, 'layout-rightpanel-active');
            
            this.rightpanelClicked = true;
            e.preventDefault();
        };
        
        this.rightpanelButton.off('click.rightpanel').on('click.rightpanel', changeRightpanelState.bind(this));
        this.rightpanelExitButton.off('click.rightpanel').on('click.rightpanel', changeRightpanelState.bind(this));
        
        this.rightpanel.off('click.rightpanel').on('click.rightpanel', function() {
            $this.rightpanelClicked = true;
        });
    },

    activate: function(item) {
        var submenu = item.children('ul');
        item.addClass('active-menuitem');

        if(submenu.length) {
            submenu.slideDown();
        }
    },

    deactivate: function(item) {
        var submenu = item.children('ul');
        item.removeClass('active-menuitem');
        
        if(submenu.length) {
            submenu.hide();
        }
    },
            
    deactivateItems: function(items, animate) {
        var $this = this;
        
        for(var i = 0; i < items.length; i++) {
            var item = items.eq(i),
            submenu = item.children('ul');
            
            if(submenu.length) {
                if(item.hasClass('active-menuitem')) {
                    var activeSubItems = item.find('.active-menuitem');
                    item.removeClass('active-menuitem');
                    
                    if(animate) {
                        submenu.slideUp('normal', function() {
                            $(this).parent().find('.active-menuitem').each(function() {
                                $this.deactivate($(this));
                            });
                        });
                    }
                    else {
                        item.find('.active-menuitem').each(function() {
                            $this.deactivate($(this));
                        });
                    }
                    
                    $this.removeMenuitem(item.attr('id'));
                    activeSubItems.each(function() {
                        $this.removeMenuitem($(this).attr('id'));
                    });
                }
                else {
                    item.find('.active-menuitem').each(function() {
                        var subItem = $(this);
                        $this.deactivate(subItem);
                        $this.removeMenuitem(subItem.attr('id'));
                    });
                }
            }
            else if(item.hasClass('active-menuitem')) {
                $this.deactivate(item);
                $this.removeMenuitem(item.attr('id'));
            }
        }
    },
    
    removeMenuitem: function (id) {
        this.expandedMenuitems = $.grep(this.expandedMenuitems, function (value) {
            return value !== id;
        });
        this.saveMenuState();
    },
    
    addMenuitem: function (id) {
        if ($.inArray(id, this.expandedMenuitems) === -1) {
            this.expandedMenuitems.push(id);
        }
        this.saveMenuState();
    },
    
    saveMenuState: function() {        
        if(this.wrapper.hasClass('layout-static'))
            $.cookie('freya_menu_static', 'freya_menu_static', {path: '/'});
        else
            $.removeCookie('freya_menu_static', {path: '/'});
        
        $.cookie('freya_expandeditems', this.expandedMenuitems.join(','), {path: '/'});
    },
    
    clearMenuState: function() {
        this.expandedMenuitems = [];
        $.removeCookie('freya_expandeditems', {path: '/'});
        $.removeCookie('freya_menu_static', {path: '/'});
    },

    clearActiveItems: function() {
        var activeItems = this.jq.find('li.active-menuitem'),
        subContainers = activeItems.children('ul');

        activeItems.removeClass('active-menuitem');
        if(subContainers && subContainers.length) {
            subContainers.hide();
        }
    },

    clearLayoutState: function() {
        this.clearMenuState();
        this.clearActiveItems();
    },
    
    restoreMenuState: function() {
        var menuCookie = $.cookie('freya_expandeditems');
        if (!this.isSlim() && !this.isHorizontal() && menuCookie) {
            this.expandedMenuitems = menuCookie.split(',');
            for (var i = 0; i < this.expandedMenuitems.length; i++) {
                var id = this.expandedMenuitems[i];
                if (id) {
                    var menuitem = $("#" + this.expandedMenuitems[i].replace(/:/g, "\\:"));
                    menuitem.addClass('active-menuitem');
                    
                    var submenu = menuitem.children('ul');
                    if(submenu.length) {
                        submenu.show();
                    }
                }
            }
        }
        
        var sidebarCookie = $.cookie('freya_menu_static');
        if(sidebarCookie) {
            this.wrapper.addClass('layout-static');
        }
  
    },
    
    removeTopbarClassFromAllItems: function(item, className, items) {
        var activeItems = item != null ? item.siblings('.' + className) : items;

        activeItems.removeClass(className);
        activeItems.children('ul').removeClass('fadeInDown');
    },
    
    addTopbarClass: function(item, className) {
        var submenu = item.children('ul');
        
        if (submenu.length) {
            if (item.hasClass(className)) {
                submenu.removeClass('fadeInDown').addClass('fadeOutUp');

                setTimeout(function() {
                    item.removeClass(className);
                    submenu.removeClass('fadeOutUp');
                }, 100);
            }
            else {
                item.addClass(className);
                submenu.addClass('fadeInDown');
            }
        }
    },

    hideTopBar: function() {
        var $this = this;
        this.topbarMenu.addClass('fadeOutUp');
        
        setTimeout(function() {
            $this.topbarMenu.removeClass('fadeOutUp topbar-menu-visible');
        },500);
    },
    
    isMobile: function() {
        return window.innerWidth < 992;
    },
    isHorizontal: function() {
        return this.wrapper.hasClass('layout-horizontal') && !this.isMobile();
    },
    isSlim: function() {
        return this.wrapper.hasClass('layout-slim') && !this.isMobile();
    },
    isStatic: function() {
        return this.wrapper.hasClass('layout-static') && !this.isMobile();
    }
});

PrimeFaces.FreyaConfigurator = {

    changeLayout: function( componentTheme, darkMode ) {
        this.changeLayoutsTheme(darkMode);
        this.changeDemo(darkMode);
        this.changeComponentsTheme(componentTheme, darkMode);
        this.changeSectionTheme( darkMode, 'layout-menu');
        this.changeSectionTheme( darkMode , 'layout-topbar');
    },

    changeLayoutsTheme: function(darkMode) {
        newLayout = '-' + darkMode;
        var linkElement = $('link[href*="layout-"]');
        var href = linkElement.attr('href');
        var startIndexOf = href.indexOf('layout-') + 6;
        var endIndexOf = href.indexOf('.css');
        var currentColor = href.substring(startIndexOf, endIndexOf);
        this.replaceLink(linkElement, href.replace(currentColor, newLayout));
    },
    
    changeDemo: function(darkMode) {
        newLayout = '-' + darkMode;
        var linkElement = $('link[href*="demo-"]');
        var href = linkElement.attr('href');
        var startIndexOf = href.indexOf('demo-') + 4;
        var endIndexOf = href.indexOf('.css');
        var currentColor = href.substring(startIndexOf, endIndexOf);
    
        this.replaceLink(linkElement, href.replace(currentColor, newLayout));
    },

    changeComponentsTheme: function(themeColor, darkMode) {
        theme = this.getColor(themeColor, darkMode);
        var library = 'primefaces-freya';
        var linkElement = $('link[href*="theme.css"]');
        var href = linkElement.attr('href');
        var index = href.indexOf(library) + 1;
        var currentTheme = href.substring(index + library.length);

        this.replaceLink(linkElement, href.replace(currentTheme, theme));
    },

    changeSectionTheme: function(theme, section) {
        var wrapperElement = $('.layout-wrapper');
     
        var styleClass = wrapperElement.attr('class');
        var tokens = styleClass.split(' ');
        var sectionClass;
        for (var i = 0; i < tokens.length; i++) {
            if (tokens[i].indexOf(section + '-') > -1) {
                sectionClass = tokens[i];
                break;
            }
        }

        wrapperElement.attr('class', styleClass.replace(sectionClass, section + '-' + theme));
    },

    changeMenuMode: function(menuMode) {
        var wrapper = $(document.body).children('.layout-wrapper');
        switch (menuMode) {
            case 'layout-sidebar':
                wrapper.addClass('layout-sidebar').removeClass('layout-slim layout-horizontal ');
                this.clearLayoutState();
            break;

            case 'layout-horizontal':
                wrapper.addClass('layout-horizontal').removeClass('layout-static  layout-slim  layout-sidebar');
                this.clearLayoutState();
            break;

            case 'layout-slim':
                wrapper.addClass('layout-slim').removeClass('layout-static layout-horizontal  layout-sidebar');
                this.clearLayoutState();
            break;

            default:
                wrapper.addClass('layout-sidebar').removeClass('layout-slim layout-horizontal  ');
                this.clearLayoutState();
            break;
        }
    },

    beforeResourceChange: function() {
        PrimeFaces.ajax.RESOURCE = null;    //prevent resource append
    },

    replaceLink: function(linkElement, href) {
        PrimeFaces.ajax.RESOURCE = 'javax.faces.Resource';
        
        var isIE = this.isIE();

        if (isIE) {
            linkElement.attr('href', href);
        }
        else {
            var cloneLinkElement = linkElement.clone(false);

            cloneLinkElement.attr('href', href);
            linkElement.after(cloneLinkElement);
            
            cloneLinkElement.off('load').on('load', function() {
                linkElement.remove();
            });

            // for dashboard
            setTimeout(function() {
                if (window['redrawChart']) {
                    window.redrawChart();
                }
            }, 100);
        }
    },
    
    getColor: function(name, darkMode) {
        return name + '-' + darkMode;
    },
    
    isIE: function() {
        return /(MSIE|Trident\/|Edge\/)/i.test(navigator.userAgent);
    },

    clearLayoutState: function() {
        var menu = PF('FreyaMenuWidget');

        if (menu) {
            menu.clearLayoutState();
        }
    },

    updateInputStyle: function(value) {
        if (value === 'filled')
            $(document.body).addClass('ui-input-filled');
        else
            $(document.body).removeClass('ui-input-filled');
    }
};

/*!
 * jQuery Cookie Plugin v1.4.1
 * https://github.com/carhartl/jquery-cookie
 *
 * Copyright 2006, 2014 Klaus Hartl
 * Released under the MIT license
 */
(function (factory) {
    if (typeof define === 'function' && define.amd) {
        // AMD (Register as an anonymous module)
        define(['jquery'], factory);
    } else if (typeof exports === 'object') {
        // Node/CommonJS
        module.exports = factory(require('jquery'));
    } else {
        // Browser globals
        factory(jQuery);
    }
}(function ($) {

    var pluses = /\+/g;

    function encode(s) {
        return config.raw ? s : encodeURIComponent(s);
    }

    function decode(s) {
        return config.raw ? s : decodeURIComponent(s);
    }

    function stringifyCookieValue(value) {
        return encode(config.json ? JSON.stringify(value) : String(value));
    }

    function parseCookieValue(s) {
        if (s.indexOf('"') === 0) {
            // This is a quoted cookie as according to RFC2068, unescape...
            s = s.slice(1, -1).replace(/\\"/g, '"').replace(/\\\\/g, '\\');
        }

        try {
            // Replace server-side written pluses with spaces.
            // If we can't decode the cookie, ignore it, it's unusable.
            // If we can't parse the cookie, ignore it, it's unusable.
            s = decodeURIComponent(s.replace(pluses, ' '));
            return config.json ? JSON.parse(s) : s;
        } catch (e) { }
    }

    function read(s, converter) {
        var value = config.raw ? s : parseCookieValue(s);
        return $.isFunction(converter) ? converter(value) : value;
    }

    var config = $.cookie = function (key, value, options) {

        // Write

        if (arguments.length > 1 && !$.isFunction(value)) {
            options = $.extend({}, config.defaults, options);

            if (typeof options.expires === 'number') {
                var days = options.expires, t = options.expires = new Date();
                t.setMilliseconds(t.getMilliseconds() + days * 864e+5);
            }

            return (document.cookie = [
                encode(key), '=', stringifyCookieValue(value),
                options.expires ? '; expires=' + options.expires.toUTCString() : '', // use expires attribute, max-age is not supported by IE
                options.path ? '; path=' + options.path : '',
                options.domain ? '; domain=' + options.domain : '',
                options.secure ? '; secure' : ''
            ].join(''));
        }

        // Read

        var result = key ? undefined : {},
            // To prevent the for loop in the first place assign an empty array
            // in case there are no cookies at all. Also prevents odd result when
            // calling $.cookie().
            cookies = document.cookie ? document.cookie.split('; ') : [],
            i = 0,
            l = cookies.length;

        for (; i < l; i++) {
            var parts = cookies[i].split('='),
                name = decode(parts.shift()),
                cookie = parts.join('=');

            if (key === name) {
                // If second argument (value) is a function it's a converter...
                result = read(cookie, value);
                break;
            }

            // Prevent storing a cookie that we couldn't decode.
            if (!key && (cookie = read(cookie)) !== undefined) {
                result[name] = cookie;
            }
        }

        return result;
    };

    config.defaults = {};

    $.removeCookie = function (key, options) {
        // Must not alter options, thus extending a fresh object...
        $.cookie(key, '', $.extend({}, options, { expires: -1 }));
        return !$.cookie(key);
    };

}));

if (PrimeFaces.widget.InputSwitch) {
    PrimeFaces.widget.InputSwitch = PrimeFaces.widget.InputSwitch.extend({

        init: function (cfg) {
            this._super(cfg);

            if (this.input.prop('checked')) {
                this.jq.addClass('ui-inputswitch-checked');
            }
        },

        check: function () {
            var $this = this;

            this.input.prop('checked', true).trigger('change');
            setTimeout(function () {
                $this.jq.addClass('ui-inputswitch-checked');
            }, 100);
        },

        uncheck: function () {
            var $this = this;

            this.input.prop('checked', false).trigger('change');
            setTimeout(function () {
                $this.jq.removeClass('ui-inputswitch-checked');
            }, 100);
        }
    });
}

if (PrimeFaces.widget.AccordionPanel) {
    PrimeFaces.widget.AccordionPanel = PrimeFaces.widget.AccordionPanel.extend({

        init: function (cfg) {
            this._super(cfg);
            
            this.headers.last().addClass('ui-accordion-header-last');
        }
    });
}