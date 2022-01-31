function submitClass() {
    var grade=document.getElementById("grade").value;
    var major = document.getElementById("major").value;
    var url = "/student/classSel/" + grade + "/" + major;
    window.location.href = url;
}
