<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>

    <title>Title</title>
    <!-- DHTMLX CSS -->
    <link rel="stylesheet" href="https://cdn.dhtmlx.com/suite/edge/suite.css">
    <!-- DHTMLX JS -->
    <script src="https://cdn.dhtmlx.com/suite/edge/suite.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>

<h3 style="margin-left: 20px" id="title">

</h3>
<div id="grid_container" style="margin: 20px; width: 600px; height: 400px;"></div>
<script>
    var grid, form;

    document.addEventListener("DOMContentLoaded", function () {

        let data = <%=request.getAttribute("kpacset")%>;
        $("#title").html(data.title);

        grid = new dhx.Grid("grid_container", {
            columns: [
                {id: "id", header: [{text: "ID"}, { content: "inputFilter" }]},
                {id: "title", header: [{text: "Title"}, { content: "inputFilter" }]},
                {id: "description", header: [{text: "Description"}, { content: "inputFilter" }]},
            ],
            data: data.kpacs,
            htmlEnable: true,
            autoWidth: true
        });

    });

</script>
</body>
</html>
