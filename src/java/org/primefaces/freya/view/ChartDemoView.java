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
package org.primefaces.freya.view;

import org.primefaces.event.ItemSelectEvent;
import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.axes.cartesian.CartesianScales;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearAxes;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearTicks;
import org.primefaces.model.charts.axes.radial.RadialScales;
import org.primefaces.model.charts.axes.radial.linear.RadialLinearAngleLines;
import org.primefaces.model.charts.axes.radial.linear.RadialLinearPointLabels;
import org.primefaces.model.charts.axes.radial.linear.RadialLinearTicks;
import org.primefaces.model.charts.bar.BarChartDataSet;
import org.primefaces.model.charts.bar.BarChartModel;
import org.primefaces.model.charts.bar.BarChartOptions;
import org.primefaces.model.charts.bubble.BubbleChartDataSet;
import org.primefaces.model.charts.bubble.BubbleChartModel;
import org.primefaces.model.charts.data.BubblePoint;
import org.primefaces.model.charts.data.NumericPoint;
import org.primefaces.model.charts.donut.DonutChartDataSet;
import org.primefaces.model.charts.donut.DonutChartModel;
import org.primefaces.model.charts.hbar.HorizontalBarChartDataSet;
import org.primefaces.model.charts.hbar.HorizontalBarChartModel;
import org.primefaces.model.charts.line.LineChartDataSet;
import org.primefaces.model.charts.line.LineChartModel;
import org.primefaces.model.charts.line.LineChartOptions;
import org.primefaces.model.charts.optionconfig.elements.Elements;
import org.primefaces.model.charts.optionconfig.elements.ElementsLine;
import org.primefaces.model.charts.optionconfig.legend.Legend;
import org.primefaces.model.charts.optionconfig.legend.LegendLabel;
import org.primefaces.model.charts.optionconfig.title.Title;
import org.primefaces.model.charts.optionconfig.tooltip.Tooltip;
import org.primefaces.model.charts.pie.PieChartDataSet;
import org.primefaces.model.charts.pie.PieChartModel;
import org.primefaces.model.charts.polar.PolarAreaChartDataSet;
import org.primefaces.model.charts.polar.PolarAreaChartModel;
import org.primefaces.model.charts.radar.RadarChartDataSet;
import org.primefaces.model.charts.radar.RadarChartModel;
import org.primefaces.model.charts.radar.RadarChartOptions;
import org.primefaces.model.charts.scatter.ScatterChartModel;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.primefaces.model.charts.axes.AxesGridLines;
import org.primefaces.model.charts.bubble.BubbleChartOptions;
import org.primefaces.model.charts.polar.PolarAreaChartOptions;

@Named
@RequestScoped
public class ChartDemoView implements Serializable {
    
    private PieChartModel pieModel;
    
    private PolarAreaChartModel polarAreaModel;
    
    private LineChartModel lineModel;
    
    private LineChartModel cartesianLinerModel;
    
    private BarChartModel barModel;
    
    private BarChartModel barModel2;
    
    private HorizontalBarChartModel hbarModel;
    
    private BarChartModel stackedBarModel;
    
    private BarChartModel stackedGroupBarModel;
    
    private RadarChartModel radarModel;
    
    private RadarChartModel radarModel2;
    
    private BubbleChartModel bubbleModel;
    
    private BarChartModel mixedModel;
    
    private DonutChartModel donutModel;
    
    private ScatterChartModel scatterModel;

    @PostConstruct
    public void init() {
        createPieModel();
        createPolarAreaModel();
        createLineModel();
        createCartesianLinerModel();
        createBarModel();
        createBarModel2();
        createHorizontalBarModel();
        createStackedBarModel();
        createStackedGroupBarModel();
        createRadarModel();
        createRadarModel2();
        createBubbleModel();
        createMixedModel();
        createDonutModel();
        createScatterModel();
    }
    
    private void createPieModel() {
        pieModel = new PieChartModel();
        ChartData data = new ChartData();
        
        PieChartDataSet dataSet = new PieChartDataSet();
        List<Number> values = new ArrayList<>();
        values.add(300);
        values.add(50);
        values.add(100);
        dataSet.setData(values);
        
        List<String> bgColors = new ArrayList<>();
        bgColors.add("rgb(241, 120, 86)");
        bgColors.add("rgb(86, 167, 245)");
        bgColors.add("rgb(233, 171, 78)");
        dataSet.setBackgroundColor(bgColors);
        
        data.addChartDataSet(dataSet);
        List<String> labels = new ArrayList<>();
        labels.add("Red");
        labels.add("Blue");
        labels.add("Yellow");
        data.setLabels(labels);
        
        pieModel.setData(data);
    }
    
    private void createPolarAreaModel() {
        polarAreaModel = new PolarAreaChartModel();
        ChartData data = new ChartData();
        
        PolarAreaChartDataSet dataSet = new PolarAreaChartDataSet();
        List<Number> values = new ArrayList<>();
        values.add(11);
        values.add(16);
        values.add(7);
        values.add(3);
        values.add(14);
        dataSet.setData(values);
        
        List<String> bgColors = new ArrayList<>();
        bgColors.add("rgb(241, 120, 86)");
        bgColors.add("rgb(87, 200, 229)");
        bgColors.add("rgb(233, 171, 78)");
        bgColors.add("rgb(123, 144, 176)");
        bgColors.add("rgb(86, 167, 245)");
        dataSet.setBackgroundColor(bgColors);
        
        data.addChartDataSet(dataSet);
        List<String> labels = new ArrayList<>();
        labels.add("Red");
        labels.add("Green");
        labels.add("Yellow");
        labels.add("Grey");
        labels.add("Blue");
        data.setLabels(labels);
        
        PolarAreaChartOptions options = new PolarAreaChartOptions();
        RadialScales cScales = new RadialScales();
        AxesGridLines gridLines = new AxesGridLines();
        gridLines.setColor("rgba(126, 146, 185, .3)");
        cScales.setGridLines(gridLines);
        options.setScales(cScales);
        
        polarAreaModel.setOptions(options);
        polarAreaModel.setData(data);
    }
    
    public void createLineModel() {
        lineModel = new LineChartModel();
        ChartData data = new ChartData();
        
        LineChartDataSet dataSet = new LineChartDataSet();
        List<Object> values = new ArrayList<>();
        values.add(65);
        values.add(59);
        values.add(40);
        values.add(51);
        values.add(66);
        values.add(75);
        values.add(80);
        dataSet.setData(values);
        dataSet.setFill(false);
        dataSet.setLabel("My First Dataset");
        dataSet.setBorderColor("rgb(87, 200, 229)");
        dataSet.setLineTension(0.1);
        data.addChartDataSet(dataSet);
        
        List<String> labels = new ArrayList<>();
        labels.add("January");
        labels.add("February");
        labels.add("March");
        labels.add("April");
        labels.add("May");
        labels.add("June");
        labels.add("July");
        data.setLabels(labels);
        
        //Options
        LineChartOptions options = new LineChartOptions();
        CartesianScales cScales = new CartesianScales();
        CartesianLinearAxes axes = new CartesianLinearAxes();
        AxesGridLines gridLines = new AxesGridLines();
        gridLines.setColor("rgba(126, 146, 185, .3)");
        gridLines.setZeroLineColor("rgba(126, 146, 185, .3)");
        axes.setGridLines(gridLines);
        cScales.addXAxesData(axes);
        cScales.addYAxesData(axes);
        options.setScales(cScales);
        
        Title title = new Title();
        title.setDisplay(true);
        title.setText("Line Chart");
        options.setTitle(title);
        
        lineModel.setOptions(options);
        lineModel.setData(data);
    }
    
    public void createScatterModel() {
        scatterModel = new ScatterChartModel();
        ChartData data = new ChartData();
        
        LineChartDataSet dataSet = new LineChartDataSet();
        List<Object> values = new ArrayList<>();
        values.add(new NumericPoint(-10, 0));
        values.add(new NumericPoint(0,10));
        values.add(new NumericPoint(10, 5));
        values.add(new NumericPoint(8, 14));
        values.add(new NumericPoint(12, 2));
        values.add(new NumericPoint(13, 7));
        values.add(new NumericPoint(6, 9));
        dataSet.setData(values);
        dataSet.setLabel("Red Dataset");
        dataSet.setBorderColor("rgb(241, 120, 86)");
        dataSet.setShowLine(false);
        data.addChartDataSet(dataSet);
        
        //Options
        LineChartOptions options = new LineChartOptions();        
        Title title = new Title();
        title.setDisplay(true);
        title.setText("Scatter Chart");
        options.setShowLines(false);
        options.setTitle(title);
        
        CartesianScales cScales = new CartesianScales();
        CartesianLinearAxes yaxes = new CartesianLinearAxes();
        AxesGridLines gridLines = new AxesGridLines();
        gridLines.setColor("rgba(126, 146, 185, .3)");
        gridLines.setZeroLineColor("rgba(126, 146, 185, .4)");
        yaxes.setGridLines(gridLines);
        cScales.addYAxesData(yaxes);
        
        CartesianLinearAxes xaxes = new CartesianLinearAxes();
        xaxes.setGridLines(gridLines);
        cScales.addXAxesData(xaxes);
        options.setScales(cScales);
        
        scatterModel.setOptions(options);
        scatterModel.setData(data);
    }
    
    public void createCartesianLinerModel() {
        cartesianLinerModel = new LineChartModel();
        ChartData data = new ChartData();
        
        LineChartDataSet dataSet = new LineChartDataSet();
        List<Object> values = new ArrayList<>();
        values.add(20);
        values.add(50);
        values.add(100);
        values.add(75);
        values.add(25);
        values.add(0);
        dataSet.setData(values);
        dataSet.setLabel("Left Dataset");
        dataSet.setYaxisID("left-y-axis");
        
        LineChartDataSet dataSet2 = new LineChartDataSet();
        List<Object> values2 = new ArrayList<>();
        values2.add(0.1);
        values2.add(0.5);
        values2.add(1.0);
        values2.add(2.0);
        values2.add(1.5);
        values2.add(0);
        dataSet2.setData(values2);
        dataSet2.setLabel("Right Dataset");
        dataSet2.setYaxisID("right-y-axis");
        
        data.addChartDataSet(dataSet);
        data.addChartDataSet(dataSet2);
        
        List<String> labels = new ArrayList<>();
        labels.add("Jan");
        labels.add("Feb");
        labels.add("Mar");
        labels.add("Apr");
        labels.add("May");
        labels.add("Jun");
        data.setLabels(labels);
        cartesianLinerModel.setData(data);
        
        //Options
        LineChartOptions options = new LineChartOptions();
        CartesianScales cScales = new CartesianScales();
        CartesianLinearAxes linearAxes = new CartesianLinearAxes();
        linearAxes.setId("left-y-axis");
        linearAxes.setPosition("left");
        CartesianLinearAxes linearAxes2 = new CartesianLinearAxes();
        linearAxes2.setId("right-y-axis");
        linearAxes2.setPosition("right");
        
        cScales.addYAxesData(linearAxes);
        cScales.addYAxesData(linearAxes2);
        options.setScales(cScales);    
        
        Title title = new Title();
        title.setDisplay(true);
        title.setText("Cartesian Linear Chart");
        options.setTitle(title);
        
        cartesianLinerModel.setOptions(options);
    }
    
    public void createBarModel() {
        barModel = new BarChartModel();
        ChartData data = new ChartData();
        
        BarChartDataSet barDataSet = new BarChartDataSet();
        barDataSet.setLabel("My First Dataset");
        
        List<Number> values = new ArrayList<>();
        values.add(65);
        values.add(59);
        values.add(80);
        values.add(81);
        values.add(56);
        values.add(55);
        values.add(40);
        barDataSet.setData(values);
        
        List<String> bgColor = new ArrayList<>();
        bgColor.add("rgba(241, 120, 86, 0.2)");
        bgColor.add("rgba(233, 171, 78, 0.2)");
        bgColor.add("rgba(255, 205, 86, 0.2)");
        bgColor.add("rgba(87, 200, 229, 0.2)");
        bgColor.add("rgba(86, 167, 245,0.2)");
        bgColor.add("rgba(153, 102, 255, 0.2)");
        bgColor.add("rgba(123, 144, 176, 0.2)");
        barDataSet.setBackgroundColor(bgColor);
        
        List<String> borderColor = new ArrayList<>();
        borderColor.add("rgb(202, 125, 202)");
        borderColor.add("rgb(233, 171, 78)");
        borderColor.add("rgb(233, 171, 78)");
        borderColor.add("rgb(87, 200, 229)");
        borderColor.add("rgb(86, 167, 245)");
        borderColor.add("rgb(241, 120, 86)");
        borderColor.add("rgb(123, 144, 176)");
        barDataSet.setBorderColor(borderColor);
        barDataSet.setBorderWidth(1);
        
        data.addChartDataSet(barDataSet);
        
        List<String> labels = new ArrayList<>();
        labels.add("January");
        labels.add("February");
        labels.add("March");
        labels.add("April");
        labels.add("May");
        labels.add("June");
        labels.add("July");
        data.setLabels(labels);
        barModel.setData(data);
        
        //Options
        BarChartOptions options = new BarChartOptions();
        CartesianScales cScales = new CartesianScales();
        CartesianLinearAxes linearAxes = new CartesianLinearAxes();
        AxesGridLines gridLines = new AxesGridLines();
        gridLines.setColor("rgba(126, 146, 185, .3)");
        linearAxes.setGridLines(gridLines);
        linearAxes.setOffset(true);
        CartesianLinearTicks ticks = new CartesianLinearTicks();
        linearAxes.setTicks(ticks);
        cScales.addYAxesData(linearAxes);
        
        CartesianLinearAxes xaxes = new CartesianLinearAxes();
        xaxes.setGridLines(gridLines);
        cScales.addXAxesData(xaxes);
        options.setScales(cScales);
        
        Title title = new Title();
        title.setDisplay(true);
        title.setText("Bar Chart");
        options.setTitle(title);

        Legend legend = new Legend();
        legend.setDisplay(true);
        legend.setPosition("top");
        LegendLabel legendLabels = new LegendLabel();
        legendLabels.setFontStyle("bold");
        legendLabels.setFontColor("#7e92b9");
        legend.setLabels(legendLabels);
        options.setLegend(legend);

        barModel.setOptions(options);
    }
    
    public void createBarModel2() {
        barModel2 = new BarChartModel();
        ChartData data = new ChartData();
        
        BarChartDataSet barDataSet = new BarChartDataSet();
        barDataSet.setLabel("My First Dataset");
        barDataSet.setBackgroundColor(("rgba(241, 120, 86, .2)"));
        barDataSet.setBorderColor(("rgb(241, 120, 86)"));
        barDataSet.setBorderWidth(1);
        List<Number> values = new ArrayList<>();
        values.add(65);
        values.add(59);
        values.add(80);
        values.add(81);
        values.add(56);
        values.add(55);
        values.add(40);
        barDataSet.setData(values);
        
        BarChartDataSet barDataSet2 = new BarChartDataSet();
        barDataSet2.setLabel("My Second Dataset");
        barDataSet2.setBackgroundColor("rgba(233, 171, 78, 0.2)");
        barDataSet2.setBorderColor("rgb(233, 171, 78)");
        barDataSet2.setBorderWidth(1);
        List<Number> values2 = new ArrayList<>();
        values2.add(85);
        values2.add(69);
        values2.add(20);
        values2.add(51);
        values2.add(76);
        values2.add(75);
        values2.add(10);
        barDataSet2.setData(values2);

        data.addChartDataSet(barDataSet);
        data.addChartDataSet(barDataSet2);
        
        List<String> labels = new ArrayList<>();
        labels.add("January");
        labels.add("February");
        labels.add("March");
        labels.add("April");
        labels.add("May");
        labels.add("June");
        labels.add("July");
        data.setLabels(labels);
        barModel2.setData(data);
        
        //Options
        BarChartOptions options = new BarChartOptions();
        CartesianScales cScales = new CartesianScales();
        CartesianLinearAxes linearAxes = new CartesianLinearAxes();
        linearAxes.setOffset(true);
        CartesianLinearTicks ticks = new CartesianLinearTicks();
        ticks.setBeginAtZero(true);
        linearAxes.setTicks(ticks);
        cScales.addYAxesData(linearAxes);
        options.setScales(cScales);
        
        Title title = new Title();
        title.setDisplay(true);
        title.setText("Bar Chart");
        options.setTitle(title);
        
        barModel2.setOptions(options);
    }
    
    public void createHorizontalBarModel() {
        hbarModel = new HorizontalBarChartModel();
        ChartData data = new ChartData();
        
        HorizontalBarChartDataSet hbarDataSet = new HorizontalBarChartDataSet();
        hbarDataSet.setLabel("My First Dataset");
        
        List<Number> values = new ArrayList<>();
        values.add(65);
        values.add(59);
        values.add(80);
        values.add(81);
        values.add(56);
        values.add(55);
        values.add(40);
        hbarDataSet.setData(values);
        
        List<String> bgColor = new ArrayList<>();
          bgColor.add("rgba(241, 120, 86, 0.2)");
        bgColor.add("rgba(233, 171, 78, 0.2)");
        bgColor.add("rgba(255, 205, 86, 0.2)");
        bgColor.add("rgba(87, 200, 229, 0.2)");
        bgColor.add("rgba(86, 167, 245,0.2)");
        bgColor.add("rgba(153, 102, 255, 0.2)");
        bgColor.add("rgba(123, 144, 176, 0.2)");
        hbarDataSet.setBackgroundColor(bgColor);
        
        List<String> borderColor = new ArrayList<>();
        borderColor.add("rgb(202, 125, 202)");
        borderColor.add("rgb(233, 171, 78)");
        borderColor.add("rgb(233, 171, 78)");
        borderColor.add("rgb(87, 200, 229)");
        borderColor.add("rgb(86, 167, 245)");
        borderColor.add("rgb(241, 120, 86)");
        borderColor.add("rgb(123, 144, 176)");
        hbarDataSet.setBorderColor(borderColor);
        hbarDataSet.setBorderWidth(1);
        
        data.addChartDataSet(hbarDataSet);
        
        List<String> labels = new ArrayList<>();
        labels.add("January");
        labels.add("February");
        labels.add("March");
        labels.add("April");
        labels.add("May");
        labels.add("June");
        labels.add("July");
        data.setLabels(labels);
        hbarModel.setData(data);
        
        //Options
        BarChartOptions options = new BarChartOptions();
        CartesianScales cScales = new CartesianScales();
        CartesianLinearAxes linearAxes = new CartesianLinearAxes();
        linearAxes.setOffset(true);
        CartesianLinearTicks ticks = new CartesianLinearTicks();
        ticks.setBeginAtZero(true);
        linearAxes.setTicks(ticks);
        cScales.addXAxesData(linearAxes);
        options.setScales(cScales);
        
        Title title = new Title();
        title.setDisplay(true);
        title.setText("Horizontal Bar Chart");
        options.setTitle(title);
        
        hbarModel.setOptions(options);
    }
    
    public void createStackedBarModel() {
        stackedBarModel = new BarChartModel();
        ChartData data = new ChartData();
        
        BarChartDataSet barDataSet = new BarChartDataSet();
        barDataSet.setLabel("Dataset 1");
        barDataSet.setBackgroundColor("rgb(241, 120, 86)");
        List<Number> dataVal = new ArrayList<>();
        dataVal.add(62);
        dataVal.add(-58);
        dataVal.add(-49);
        dataVal.add(25);
        dataVal.add(4);
        dataVal.add(77);
        dataVal.add(-41);
        barDataSet.setData(dataVal);
        
        BarChartDataSet barDataSet2 = new BarChartDataSet();
        barDataSet2.setLabel("Dataset 2");
        barDataSet2.setBackgroundColor("rgb(86, 167, 245)");
        List<Number> dataVal2 = new ArrayList<>();
        dataVal2.add(-1);
        dataVal2.add(32);
        dataVal2.add(-52);
        dataVal2.add(11);
        dataVal2.add(97);
        dataVal2.add(76);
        dataVal2.add(-78);
        barDataSet2.setData(dataVal2);
        
        BarChartDataSet barDataSet3 = new BarChartDataSet();
        barDataSet3.setLabel("Dataset 3");
        barDataSet3.setBackgroundColor("rgb(87, 200, 229)");
        List<Number> dataVal3 = new ArrayList<>();
        dataVal3.add(-44);
        dataVal3.add(25);
        dataVal3.add(15);
        dataVal3.add(92);
        dataVal3.add(80);
        dataVal3.add(-25);
        dataVal3.add(-11);
        barDataSet3.setData(dataVal3);
        
        data.addChartDataSet(barDataSet);
        data.addChartDataSet(barDataSet2);
        data.addChartDataSet(barDataSet3);
        
        List<String> labels = new ArrayList<>();
        labels.add("January");
        labels.add("February");
        labels.add("March");
        labels.add("April");
        labels.add("May");
        labels.add("June");
        labels.add("July");
        data.setLabels(labels);
        stackedBarModel.setData(data);
        
        //Options
        BarChartOptions options = new BarChartOptions();
        CartesianScales cScales = new CartesianScales();
        CartesianLinearAxes linearAxes = new CartesianLinearAxes();
        linearAxes.setStacked(true);
        linearAxes.setOffset(true);
        cScales.addXAxesData(linearAxes);
        cScales.addYAxesData(linearAxes);
        options.setScales(cScales);
        
        Title title = new Title();
        title.setDisplay(true);
        title.setText("Bar Chart - Stacked");
        options.setTitle(title);
        
        Tooltip tooltip = new Tooltip();
        tooltip.setMode("index");
        tooltip.setIntersect(false);
        options.setTooltip(tooltip);  
        
        stackedBarModel.setOptions(options);
    }
    
    public void createStackedGroupBarModel() {
        stackedGroupBarModel = new BarChartModel();
        ChartData data = new ChartData();
        
        BarChartDataSet barDataSet = new BarChartDataSet();
        barDataSet.setLabel("Dataset 1");
        barDataSet.setBackgroundColor("rgb(241, 120, 86)");
        barDataSet.setStack("Stack 0");
        List<Number> dataVal = new ArrayList<>();
        dataVal.add(-32);
        dataVal.add(-70);
        dataVal.add(-33);
        dataVal.add(30);
        dataVal.add(-49);
        dataVal.add(23);
        dataVal.add(-92);
        barDataSet.setData(dataVal);
        
        BarChartDataSet barDataSet2 = new BarChartDataSet();
        barDataSet2.setLabel("Dataset 2");
        barDataSet2.setBackgroundColor("rgb(86, 167, 245)");
        barDataSet2.setStack("Stack 0");
        List<Number> dataVal2 = new ArrayList<>();
        dataVal2.add(83);
        dataVal2.add(18);
        dataVal2.add(86);
        dataVal2.add(8);
        dataVal2.add(34);
        dataVal2.add(46);
        dataVal2.add(11);
        barDataSet2.setData(dataVal2);
        
        BarChartDataSet barDataSet3 = new BarChartDataSet();
        barDataSet3.setLabel("Dataset 3");
        barDataSet3.setBackgroundColor("rgb(87, 200, 229)");
        barDataSet3.setStack("Stack 1");
        List<Number> dataVal3 = new ArrayList<>();
        dataVal3.add(-45);
        dataVal3.add(73);
        dataVal3.add(-25);
        dataVal3.add(65);
        dataVal3.add(49);
        dataVal3.add(-18);
        dataVal3.add(46);
        barDataSet3.setData(dataVal3);
        
        data.addChartDataSet(barDataSet);
        data.addChartDataSet(barDataSet2);
        data.addChartDataSet(barDataSet3);
        
        List<String> labels = new ArrayList<>();
        labels.add("January");
        labels.add("February");
        labels.add("March");
        labels.add("April");
        labels.add("May");
        labels.add("June");
        labels.add("July");
        data.setLabels(labels);
        stackedGroupBarModel.setData(data);
        
        //Options
        BarChartOptions options = new BarChartOptions();
        CartesianScales cScales = new CartesianScales();
        CartesianLinearAxes linearAxes = new CartesianLinearAxes();
        linearAxes.setStacked(true);
        linearAxes.setOffset(true);
        cScales.addXAxesData(linearAxes);
        cScales.addYAxesData(linearAxes);
        options.setScales(cScales);
        
        Title title = new Title();
        title.setDisplay(true);
        title.setText("Bar Chart - Stacked Group");
        options.setTitle(title);
        
        Tooltip tooltip = new Tooltip();
        tooltip.setMode("index");
        tooltip.setIntersect(false);
        options.setTooltip(tooltip);  
        
        stackedGroupBarModel.setOptions(options);
    }
    
    public void createRadarModel() {
        radarModel = new RadarChartModel();
        ChartData data = new ChartData();
        
        RadarChartDataSet radarDataSet = new RadarChartDataSet();
        radarDataSet.setLabel("My First Dataset");
        radarDataSet.setFill(true);
        radarDataSet.setBackgroundColor("rgba(241, 120, 86, 0.2)");
        radarDataSet.setBorderColor("rgb(241, 120, 86)");
        radarDataSet.setPointBackgroundColor("rgb(241, 120, 86)");
        radarDataSet.setPointBorderColor("#fff");
        radarDataSet.setPointHoverBackgroundColor("#fff");
        radarDataSet.setPointHoverBorderColor("rgb(241, 120, 86)");
        List<Number> dataVal = new ArrayList<>();
        dataVal.add(65);
        dataVal.add(59);
        dataVal.add(90);
        dataVal.add(81);
        dataVal.add(56);
        dataVal.add(55);
        dataVal.add(40);
        radarDataSet.setData(dataVal);
        
        RadarChartDataSet radarDataSet2 = new RadarChartDataSet();
        radarDataSet2.setLabel("My Second Dataset");
        radarDataSet2.setFill(true);
        radarDataSet2.setBackgroundColor("rgba(86, 167, 245, 0.2)");
        radarDataSet2.setBorderColor("rgb(86, 167, 245)");
        radarDataSet2.setPointBackgroundColor("rgb(86, 167, 245)");
        radarDataSet2.setPointBorderColor("#fff");
        radarDataSet2.setPointHoverBackgroundColor("#fff");
        radarDataSet2.setPointHoverBorderColor("rgb(86, 167, 245)");
        List<Number> dataVal2 = new ArrayList<>();
        dataVal2.add(28);
        dataVal2.add(48);
        dataVal2.add(40);
        dataVal2.add(19);
        dataVal2.add(96);
        dataVal2.add(27);
        dataVal2.add(100);
        radarDataSet2.setData(dataVal2);
        
        data.addChartDataSet(radarDataSet);
        data.addChartDataSet(radarDataSet2);
        
        List<String> labels = new ArrayList<>();
        labels.add("Eating");
        labels.add("Drinking");
        labels.add("Sleeping");
        labels.add("Designing");
        labels.add("Coding");
        labels.add("Cycling");
        labels.add("Running");
        data.setLabels(labels);
        
        /* Options */
        RadarChartOptions options = new RadarChartOptions();
        Elements elements = new Elements();
        ElementsLine elementsLine = new ElementsLine();
        elementsLine.setTension(0);
        elementsLine.setBorderWidth(3);
        elements.setLine(elementsLine);
        options.setElements(elements);
        
        RadialScales cScales = new RadialScales();
        AxesGridLines gridLines = new AxesGridLines();
        gridLines.setColor("rgba(126, 146, 185, .3)");
        cScales.setGridLines(gridLines);
        
        RadialLinearAngleLines rAngleLines = new RadialLinearAngleLines();
        rAngleLines.setColor("rgba(126, 146, 185, .3)");
        cScales.setAngelLines(rAngleLines);
        
        options.setScales(cScales);
        
        radarModel.setOptions(options);
        radarModel.setData(data);
    }
    
    public void createRadarModel2() {
        radarModel2 = new RadarChartModel();
        ChartData data = new ChartData();
        
        RadarChartDataSet radarDataSet = new RadarChartDataSet();
        radarDataSet.setLabel("P.Practitioner");
        radarDataSet.setLineTension(0.1);
        radarDataSet.setBackgroundColor("rgba(87, 200, 229, 0.2)");
        radarDataSet.setBorderColor("rgba(87, 200, 229, 1)");
        radarDataSet.setPointBackgroundColor("rgba(87, 200, 229, 1)");
        radarDataSet.setPointBorderColor("#fff");
        radarDataSet.setPointHoverRadius(5);
        radarDataSet.setPointHoverBackgroundColor("#fff");
        radarDataSet.setPointHoverBorderColor("rgba(87, 200, 229, 1)");
        List<Number> dataVal = new ArrayList<>();
        dataVal.add(2);
        dataVal.add(3);
        dataVal.add(2);
        dataVal.add(1);
        dataVal.add(3);
        radarDataSet.setData(dataVal);

        RadarChartDataSet radarDataSet2 = new RadarChartDataSet();
        radarDataSet2.setLabel("P.Manager");
        radarDataSet2.setLineTension(0.1);
        radarDataSet2.setBackgroundColor("rgba(233, 171, 78, 0.2)");
        radarDataSet2.setBorderColor("rgba(233, 171, 78, 1)");
        radarDataSet2.setPointBackgroundColor("rgba(233, 171, 78, 1)");
        radarDataSet2.setPointBorderColor("#fff");
        radarDataSet2.setPointHoverRadius(5);
        radarDataSet2.setPointHoverBackgroundColor("#fff");
        radarDataSet2.setPointHoverBorderColor("rgba(233, 171, 78, 1)");
        List<Number> dataVal2 = new ArrayList<>();
        dataVal2.add(2);
        dataVal2.add(3);
        dataVal2.add(3);
        dataVal2.add(2);
        dataVal2.add(3);
        radarDataSet2.setData(dataVal2);
        
        data.addChartDataSet(radarDataSet);
        data.addChartDataSet(radarDataSet2);
        
        List<List<String>> labels = new ArrayList<>();
        labels.add(new ArrayList(Arrays.asList("Process","Excellence")));
        labels.add(new ArrayList(Arrays.asList("Problem","Solving")));
        labels.add(new ArrayList(Arrays.asList("Facilitation")));
        labels.add(new ArrayList(Arrays.asList("Project","Mgmt")));
        labels.add(new ArrayList(Arrays.asList("Change","Mgmt")));
        data.setLabels(labels);

        /* Options */
        RadarChartOptions options = new RadarChartOptions();
        RadialScales rScales = new RadialScales();
        
        RadialLinearAngleLines angelLines = new RadialLinearAngleLines();
        angelLines.setDisplay(true);
        angelLines.setLineWidth(0.5);
        angelLines.setColor("rgba(128, 128, 128, 0.2)");
        rScales.setAngelLines(angelLines);
        
        RadialLinearPointLabels pointLabels = new RadialLinearPointLabels();
        pointLabels.setFontSize(14);
        pointLabels.setFontStyle("300");
        pointLabels.setFontColor("#7e92b9");
        pointLabels.setFontFamily("Lato, sans-serif");
        
        RadialLinearTicks ticks = new RadialLinearTicks();
        ticks.setBeginAtZero(true);
        ticks.setMaxTicksLimit(3);
        ticks.setMin(0);
        ticks.setMax(3);
        ticks.setDisplay(false);

        options.setScales(rScales);
        
        radarModel2.setOptions(options);
        radarModel2.setData(data);
        radarModel2.setExtender("skinRadarChart");
    }
    
    public void createBubbleModel() {
        bubbleModel = new BubbleChartModel();
        ChartData data = new ChartData();
        
        BubbleChartDataSet dataSet = new BubbleChartDataSet();
        List<BubblePoint> values = new ArrayList<>();
        values.add(new BubblePoint(20, 30, 15));
        values.add(new BubblePoint(40, 10, 10));
        dataSet.setData(values);
        dataSet.setBackgroundColor("rgb(241, 120, 86)");
        dataSet.setLabel("First Dataset");
        data.addChartDataSet(dataSet);
        
        BubbleChartOptions options = new BubbleChartOptions();
        CartesianScales cScales = new CartesianScales();
        CartesianLinearAxes yaxes = new CartesianLinearAxes();
        AxesGridLines gridLines = new AxesGridLines();
        gridLines.setColor("rgba(126, 146, 185, .3)");
        yaxes.setGridLines(gridLines);
        cScales.addYAxesData(yaxes);
        
        CartesianLinearAxes xaxes = new CartesianLinearAxes();
        xaxes.setGridLines(gridLines);
        cScales.addXAxesData(xaxes);
        options.setScales(cScales);
        
        bubbleModel.setOptions(options);
        bubbleModel.setData(data);
    }
    
    public void createMixedModel() {
        mixedModel = new BarChartModel();
        ChartData data = new ChartData();
        
        BarChartDataSet dataSet = new BarChartDataSet();
        List<Number> values = new ArrayList<>();
        values.add(10);
        values.add(20);
        values.add(30);
        values.add(40);
        dataSet.setData(values);
        dataSet.setLabel("Bar Dataset");
        dataSet.setBorderColor("rgb(241, 120, 86)");
        dataSet.setBackgroundColor("rgba(241, 120, 86, 0.2)");
        
        LineChartDataSet dataSet2 = new LineChartDataSet();
        List<Object> values2 = new ArrayList<>();
        values2.add(50);
        values2.add(50);
        values2.add(50);
        values2.add(50);
        dataSet2.setData(values2);
        dataSet2.setLabel("Line Dataset");
        dataSet2.setFill(false);
        dataSet2.setBorderColor("rgb(86, 167, 245)");
        
        data.addChartDataSet(dataSet);
        data.addChartDataSet(dataSet2);
        
        List<String> labels = new ArrayList<>();
        labels.add("January");
        labels.add("February");
        labels.add("March");
        labels.add("April");
        data.setLabels(labels);
        
        mixedModel.setData(data);
        
        //Options
        BarChartOptions options = new BarChartOptions();
        CartesianScales cScales = new CartesianScales();
        CartesianLinearAxes linearAxes = new CartesianLinearAxes();
        AxesGridLines gridLines = new AxesGridLines();
        gridLines.setColor("rgba(126, 146, 185, .3)");
        linearAxes.setGridLines(gridLines);
        linearAxes.setOffset(true);
        CartesianLinearTicks ticks = new CartesianLinearTicks();
        linearAxes.setTicks(ticks);
        cScales.addYAxesData(linearAxes);
        
        CartesianLinearAxes xaxes = new CartesianLinearAxes();
        xaxes.setGridLines(gridLines);
        cScales.addXAxesData(xaxes);
        
        options.setScales(cScales);
        mixedModel.setOptions(options);
    }
    
    public void createDonutModel() {
        donutModel = new DonutChartModel();
        ChartData data = new ChartData();
        
        DonutChartDataSet dataSet = new DonutChartDataSet();
        List<Number> values = new ArrayList<>();
        values.add(300);
        values.add(50);
        values.add(100);
        dataSet.setData(values);
        
        List<String> bgColors = new ArrayList<>();
        bgColors.add("rgb(241, 120, 86)");
        bgColors.add("rgb(86, 167, 245)");
        bgColors.add("rgb(233, 171, 78)");
        dataSet.setBackgroundColor(bgColors);
        
        data.addChartDataSet(dataSet);
        List<String> labels = new ArrayList<>();
        labels.add("Red");
        labels.add("Blue");
        labels.add("Yellow");
        data.setLabels(labels);
        
        donutModel.setData(data);
    }

    public void itemSelect(ItemSelectEvent event) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Item selected",
                "Item Index: " + event.getItemIndex() + ", DataSet Index:" + event.getDataSetIndex());

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public PieChartModel getPieModel() {
        return pieModel;
    }

    public void setPieModel(PieChartModel pieModel) {
        this.pieModel = pieModel;
    }

    public PolarAreaChartModel getPolarAreaModel() {
        return polarAreaModel;
    }

    public void setPolarAreaModel(PolarAreaChartModel polarAreaModel) {
        this.polarAreaModel = polarAreaModel;
    }

    public LineChartModel getLineModel() {
        return lineModel;
    }

    public void setLineModel(LineChartModel lineModel) {
        this.lineModel = lineModel;
    }

    public LineChartModel getCartesianLinerModel() {
        return cartesianLinerModel;
    }

    public void setCartesianLinerModel(LineChartModel cartesianLinerModel) {
        this.cartesianLinerModel = cartesianLinerModel;
    }
    
    public BarChartModel getBarModel() {
        return barModel;
    }

    public void setBarModel(BarChartModel barModel) {
        this.barModel = barModel;
    }

    public BarChartModel getBarModel2() {
        return barModel2;
    }

    public void setBarModel2(BarChartModel barModel2) {
        this.barModel2 = barModel2;
    }

    public HorizontalBarChartModel getHbarModel() {
        return hbarModel;
    }

    public void setHbarModel(HorizontalBarChartModel hbarModel) {
        this.hbarModel = hbarModel;
    }

    public BarChartModel getStackedBarModel() {
        return stackedBarModel;
    }

    public void setStackedBarModel(BarChartModel stackedBarModel) {
        this.stackedBarModel = stackedBarModel;
    }

    public BarChartModel getStackedGroupBarModel() {
        return stackedGroupBarModel;
    }

    public void setStackedGroupBarModel(BarChartModel stackedGroupBarModel) {
        this.stackedGroupBarModel = stackedGroupBarModel;
    }

    public RadarChartModel getRadarModel() {
        return radarModel;
    }

    public void setRadarModel(RadarChartModel radarModel) {
        this.radarModel = radarModel;
    }

    public RadarChartModel getRadarModel2() {
        return radarModel2;
    }

    public void setRadarModel2(RadarChartModel radarModel2) {
        this.radarModel2 = radarModel2;
    }

    public BubbleChartModel getBubbleModel() {
        return bubbleModel;
    }

    public void setBubbleModel(BubbleChartModel bubbleModel) {
        this.bubbleModel = bubbleModel;
    }

    public BarChartModel getMixedModel() {
        return mixedModel;
    }

    public void setMixedModel(BarChartModel mixedModel) {
        this.mixedModel = mixedModel;
    }

    public DonutChartModel getDonutModel() {
        return donutModel;
    }

    public void setDonutModel(DonutChartModel donutModel) {
        this.donutModel = donutModel;
    }
    
    public ScatterChartModel getScatterModel() {
        return scatterModel;
    }
    
    public void setScatterModel(ScatterChartModel scatterModel) {
        this.scatterModel = scatterModel;
    }
}
