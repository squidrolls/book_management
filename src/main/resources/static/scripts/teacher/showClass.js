function submitTime() {
    var startYear=document.getElementById("startYear").value;
    var startTerm = document.getElementById("startTerm").value;
    var url = "/teacher/classes/" + startYear + "/" + startTerm;
    window.location.href = url;
}

function submitIntro() {
    var intro=document.getElementById("ad-intro").value;
    var course_id = document.getElementById("classInfo").rows[0].cells[0].innerText;
    console.log(course_id)
    var JSONdata=JSON.stringify(intro);
    console.log(intro);
    var path=course_id+"/changeintro";
    console.log(path);
    $.ajax({
        type: "post",
        url: path,
        data: JSONdata,
        dataType: "json",
        async:false,
        contentType: "application/json;charset=UTF-8",
        success: function (result) {
            if (result) {
                $('#add1').modal('hide');
                window.location.reload();
                alert("添加成功");
            } else {
                $('#add1').modal('hide');
                alert("添加失败");
            }
        }
    });
}


