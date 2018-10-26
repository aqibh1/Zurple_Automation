<html>
<head><title>My Custom Report</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <#list cases?keys as caseTitle>
        <div class="row">
            <div class="col-md-12">
                <h3>${caseTitle}</h3>
                <#list cases[caseTitle] as workflow>
                    <table class="table">
                        <thead>
                        <tr>
                            <th>Suite</th>
                            <th>Action</th>
                            <th>Method</th>
                            <th>Status</th>
                            <th>Screenshot</th>
                            <th>Execution Time(ms)</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list workflow as test>
                            <tr class="<#if test.testResultCode == 'PASSED'>success</#if><#if test.testResultCode == 'SKIPPED'>warning</#if><#if test.testResultCode == 'FAILED'>danger</#if>">
                                <td>${test.suiteName}</td>
                                <td>${test.testName}</td>
                                <td>${test.methodName}</td>
                                <td>${test.testResultCode}</td>
                                <td>
                                    <#if test.screenshot != ''>
                                        <img height="100" width="100" src="${test.screenshot}"></td>
                                    </#if>
                                <td>${test.time}</td>
                            </tr>
                        </#list>
                        </tbody>
                    </table>
                </#list>
            </div>
        </div>
    </#list>

</div>
</body>
</html>