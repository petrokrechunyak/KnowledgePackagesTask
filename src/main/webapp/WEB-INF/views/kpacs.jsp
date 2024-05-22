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

<div style="margin: 0">
        <h3 style="margin-left: 20px">
            Creating new Knowledge Package
        </h3>
        <form style="margin: 20px; height: max-content;" id="form"></form>
</div>
<div id="grid_container" style="margin: 20px; width: 600px; height: 400px;"></div>
<script>
    var grid, form;

    function deleteRow(id) {
        $.ajax({
            url: `/kpacs/${id}`,
            type: 'DELETE',
            success: function(result) {
                grid.data.remove(id);
            },
            error: function(error) {
                console.error('Error:', error);
            }
        });
    }

    document.addEventListener("DOMContentLoaded", function () {

        let data = <%=request.getAttribute("kpacs")%>;
        data.forEach(function (x) {
            x.delete = "<span style='cursor: pointer' onclick='deleteRow(" + x.id + ")'><span class='glyphicon glyphicon-trash'></span></span>";
        })

        form = new dhx.Form("form", {
            width: 600,
            css: "dhx_widget--bg_white dhx_widget--bordered",
            padding: 10,
            rows: [
                {
                    name: "title",
                    type: "input",
                    label: "Title",
                    placeholder: "Some name"
                },
                {
                    name: "description",
                    type: "input",
                    label: "Description",
                    placeholder: "Some desc"
                },
                {
                    type: "button",
                    text: "Send",
                    id: "send",
                    submit: true
                }
            ]
        });

        grid = new dhx.Grid("grid_container", {
            columns: [
                {id: "id", header: [{text: "ID"}, { content: "inputFilter" }]},
                {id: "title", header: [{text: "Title"}, { content: "inputFilter" }]},
                {id: "description", header: [{text: "Description"}, { content: "inputFilter" }]},
                {id: "creationDate", header: [{text: "Creation Date"}, { content: "inputFilter" }]},
                {id: "delete", header: [{text: "Delete"}]}
            ],
            data: data,
            htmlEnable: true,
            autoWidth: true
        });



        form.events.on("ButtonClick", function(name, ev) {
            if (name === "send") {
                form.send("/kpacs", "POST")
                    .then(response => {
                        window.location.reload();
                    })
                    .catch(error => {
                        console.error(error); // Handle error response
                    });
            }
        });

    });

</script>
</body>
</html>
