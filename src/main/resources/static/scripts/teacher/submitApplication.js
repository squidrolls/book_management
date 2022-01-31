function submitApplication(){
    if( $('#buyApplicationForm').form('is valid')){
        var opnenId = document.getElementById("openId").value;
        var isbn = document.getElementById("isbn").value;
        var bookName = document.getElementById("bookName").value;
        var bookAuthor = document.getElementById("bookAuthor").value;
        var press = document.getElementById("press").value;
        var publishDate = document.getElementById("publishDate").value;
        var buyBook = {
            'openId': opnenId, 'isbn': isbn, 'bookName': bookName,
            'author': bookAuthor, 'press': press, 'publishDate': publishDate,
        }
        console.log(buyBook);
        var JSONdata = JSON.stringify(buyBook);

        $.ajax({
            type: "post",
            url: "/teacher/buyapplication",
            data: JSONdata,
            dataType: "json",
            async:false,
            contentType: "application/json;charset=UTF-8",
            success : function(result) {
                if (result) {
                    alert("提交成功");
                } else {
                    alert("提交失败");
                }
            }
        });
    }
}

function resetApplication(){
    $("#buyApplicationForm").form('clear')
}