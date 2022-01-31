//控制垂直导航栏子功能的隐藏和显示
$(document).ready(function () {
    $("#click_2").click(function () {
        $("#fun1,#fun2,#fun3").toggle();
    });
    $("#click_3").click(function () {
        $("#fun4,#fun5").toggle();
    });
    $('.dropdown').dropdown({transition: 'drop', on: 'hover' });
});