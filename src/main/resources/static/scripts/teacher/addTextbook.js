function turnToAddPage() {
    var local_url = window.location.href;
    let index = local_url.lastIndexOf("\/");
    let str  = local_url.substring(0, index+1);
    let sub_url = str.substring(0,str.length-1)
    let url = sub_url +'/addtextbook'

    window.location.href = url;
}

function addTextbook(e){
    var trobj = e.parentNode.parentNode; //获得按钮所在行的行对象
    var tdobj = trobj.getElementsByTagName("td");
    var isbn = tdobj[0].innerText;

    var local_url = window.location.href;
    let index = local_url.lastIndexOf("\/");
    let str  = local_url.substring(0, index+1);
    let openId = str.substring(str.length-15,str.length-1)
    var chooseTextbookKey = {
        'openId': openId, 'isbn': isbn
    }
    console.log(chooseTextbookKey);

    var JSONdata = JSON.stringify(chooseTextbookKey);

    $.ajax({
        type: "post",
        url: window.location.href,
        data: JSONdata,
        dataType: "json",
        async:false,
        contentType: "application/json;charset=UTF-8",
        success : function(result) {
            if (result) {
                alert("添加成功");
            } else {
                alert("添加失败");
            }
        }
    });

}

function deleteTextbook(e){
    var trobj = e.parentNode.parentNode; //获得按钮所在行的行对象
    var tdobj = trobj.getElementsByTagName("td");
    var isbn = tdobj[0].innerText;

    var local_url = window.location.href;
    let index = local_url.lastIndexOf("\/");
    let str  = local_url.substring(0, index+1);
    let openId = str.substring(str.length-15,str.length-1)
    var chooseTextbookKey = {
        'openId': openId, 'isbn': isbn
    }
    console.log(chooseTextbookKey);

    var JSONdata = JSON.stringify(chooseTextbookKey);

    $.ajax({
        type: "post",
        url: "/teacher/classes/"+openId+"/showthistextbook",
        data: JSONdata,
        dataType: "json",
        async:false,
        contentType: "application/json;charset=UTF-8",
        success : function(result) {
            if (result) {
                window.location.reload();
                alert("删除成功");
            } else {
                alert("删除失败");
            }
        }
    });


}

function submitAddBookSearchContent() {
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
    var local_url = window.location.href;
    window.location.href = local_url + "/" +searchMethod+"/"+searchContent;

}