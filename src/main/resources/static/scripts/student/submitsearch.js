function submitsearch() {
    var searchContent = document.getElementById("classname").value;
    var url = "/student/classSearch/" +searchContent;
    window.location.href = url;
}