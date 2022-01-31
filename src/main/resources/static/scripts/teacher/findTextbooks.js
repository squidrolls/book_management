function submitBookSearchContent() {
    var searchType = document.getElementById("searchMethod").value;
    var searchContent = document.getElementById("searchContent").value;
    var searchMethod;
    if (searchType === "根据ISBN查找") {
        searchMethod = "ISBN";
    } else if (searchType === "根据书名查询") {
        searchMethod = "bookname"
    } else {
        searchMethod = "author"
    }
    var url = "/teacher/findbookinfo/" +searchMethod+"/"+searchContent;
    window.location.href = url;
}

function submitEBookSearchContent() {
    var searchType = document.getElementById("searchMethod").value;
    var searchContent = document.getElementById("searchContent").value;
    var searchMethod;
    if (searchType === "根据ISBN查找") {
        searchMethod = "ISBN";
    } else if (searchType === "根据书名查询") {
        searchMethod = "bookname"
    } else {
        searchMethod = "author"
    }
    var url = "/teacher/findebook/" +searchMethod+"/"+searchContent;
    window.location.href = url;
}