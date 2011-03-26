<!-- automatic include document -->
<#list dataList as specData>
    <p spacingBefore="6.0" spacingAfter="6.0">Специальность <b>"${specData.name}"</b></p>
<#assign x = 1>
<#list specData.captains as captain>
    <p firstLineIndent="40">${x}. <b>${captain.student.lastName} ${captain.student.firstName} ${captain.student.middleName} - ${captain.group.name} группа</b></p>
<#assign x = x + 1>
</#list>
</#list>