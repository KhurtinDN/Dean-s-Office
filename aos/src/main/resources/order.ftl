<?xml version="1.0"?>
<!-- <fonts>
	<font name="" style="" path="" />
</fonts> -->
<document
	 xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	 xsi:noNamespaceSchemaLocation="document.xsd"
	 type="PDF" name="uchcard.pdf" format="A4" marginLeft="72">
	<p align="center" spacingAfter="6.0">Федеральное агенство по образованию</p>
    <p align="center"><b><var name="SSU_ONLY_HEADER" />
<var name="SSU_BY_HEADER" /></b></p>
    <p align="center">факультет <var name="FACULTY_FULLNAME" /></p>
    <p align="center" spacingAfter="5.0" spacingBefore="5.0"><font size="10">ДНЕВНОЕ ОТДЕЛЕНИЕ</font></p>
    <p align="center" spacingAfter="10.0" spacingBefore="5.0"><font size="14"><b>ПРИКАЗ</b></font></p>
    <font size="14"><table columns="3" width="100" align="center" border="0">
        <tr>
            <td align="center">"___"______20___г.</td>
            <td align="center">Саратов</td>
            <td align="center">№__________</td>
        </tr>
    </table></font>
    <p spacingAfter="5.0" spacingBefore="5.0"><font size="12"><var name="ORDER_NOTE" /></font></p>
    <p spacingBefore="4.0"><b>ПРИКАЗЫВАЮ:</b></p>
<#assign x = 1>
<#list directives as directive>
    <p spacingBefore="4.0" align="center"><b>${x}</b></p>
    <p firstLineIndent="40">${directive.data.description}</p>
${directive.data.body}
    <p> </p>
    <table columns="2" width="100" align="center" border="0">
        <tr>
            <td align="right" valign="top">Основание:</td>
            <td align="left">${directive.data.grounds}</td>
        </tr>
    </table>
<#assign x = x + 1>
</#list>
    <p> </p>
    <b><table columns="2" width="100" align="center" border="0">
        <tr>
            <td align="left"><var name="SUPERVISOR_POSITION" />,</td>
            <td align="left"></td>
        </tr>
        <tr>
            <td align="left"> <var name="SUPERVISOR_DEGREE" /></td>
            <td align="right"><var name="SUPERVISOR_NAME" /></td>
        </tr>
    </table></b>
    <p spacingBefore="6.0"><b>Согласовано:
<var name="COORDINATORS_LIST" /></b></p>
</document>