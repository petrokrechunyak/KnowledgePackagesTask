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
        Creating new Knowledge Package Set
    </h3>
    <form style="margin: 20px; height: max-content;" id="form"></form>

</div>

<div id="grid_container" style="margin: 20px; width: 600px; height: 400px;"></div>
<script>
    var grid, form;

    function deleteRow(id) {
        $.ajax({
            url: `/sets/${id}`,
            type: 'DELETE',
            success: function (result) {
                grid.data.remove(id);
            },
            error: function (error) {
                console.error('Error:', error);
            }
        });
    }

    document.addEventListener("DOMContentLoaded", function () {

        let data = <%=request.getAttribute("kpacsets")%>;
        data.forEach(function (x) {
            x.delete = "<span style='cursor: pointer' onclick='deleteRow(" + x.id + ")'><span class='glyphicon glyphicon-trash'></span></span>";
        })

        let kpacs = <%=request.getAttribute("kpacs")%>;
        kpacs.forEach(function (x) {
            x.value = x.title;
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
                    type: "combo",
                    name: "kPacs",
                    label: "Knowledge packages",
                    data: kpacs,
                    multiselection: true
                },
                {
                    type: "button",
                    text: "Send",
                    id: "send",
                    submit: true
                }
            ]
        });
        form.events.on("ButtonClick", function (name, ev) {
            if (name === "send") {
                form._state.arr = filterById(form._state.kPacs, kpacs);
                form.send("/sets", "POST")
                    .then(response => {
                        window.location.reload();
                    })
                    .catch(error => {
                        console.error(error); // Handle error response
                    });
            }
        });

        grid = new dhx.Grid("grid_container", {
            columns: [
                {id: "title", header: [{text: "Title"}, {content: "inputFilter"}]},
                {id: "delete", header: [{text: "Delete"}]}
            ],
            data: data,
            htmlEnable: true,
            autoWidth: true
        });

        grid.events.on("cellDblClick", function (row, column) {
            var newWindow = window.open("sets/" + row.id, "name");

            if (window.focus) { // for me this window still open as new tab, but it might be because of my browser settings
                newWindow.focus();
            }
        });

    });

    function filterById(selected, allOptions) {
        let arr = [];
        selected.forEach(function (x) {
            allOptions.forEach(function (y) {
                if(x === y.id) {
                    arr.push(y);
                }
            })
        })
        return arr;
    }

</script>
</body>
</html>
