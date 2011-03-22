<!-- automatic include document -->
<p spacingAfter="6.0">Список студентов</p>
<table columns="5" width="100" widths="1 13 3 5 6" align="left" border="15">
<#list studentMaps as studentEntry>
    <tr>
        <td align="right">${studentEntry_index + 1}</td>
        <td align="left">${studentEntry.student.lastName} ${studentEntry.student.firstName} ${studentEntry.student.middleName}</td>
        <td align="center">${studentEntry.student.group.name}</td>
        <td align="center">с ${studentEntry.stipendStartDate}</td>
        <td align="center">по ${studentEntry.stipendEndDate}</td>
    </tr>
</#list>
</table>