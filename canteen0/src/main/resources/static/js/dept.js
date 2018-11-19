function initDept() {

    $.post("Dept", {"method": "list"},
        function (data) {
            /* alert(data)*/
            var content;
            if (data != null && data.length > 0) {
                for (var i in data) {
                    content += "<option value=" + data[i].id + ">" + data[i].name + "</option>"
                }
            }
            $("#dept").append(content);
        }, "json");
}

function getEmpno() {

    var deptId = $("#dept").val()

    if (deptId == 0) {
        document.getElementById("empno").innerText = "";
    } else {
        $.post("emp/Emp", {"method": "getEmpno", "deptId": deptId},
            function (data) {
                document.getElementById("empno").innerText = data;
            }, "json")
    }

}