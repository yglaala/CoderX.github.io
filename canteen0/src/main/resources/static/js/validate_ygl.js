function testPositiveInteger(number){
    var n = /^[1-9]\d*$/;
    if(!n.test(number)){
        alert('请输入正整数')
        return false;
    }
    return true;
}

function testFloat(val)
{
    if($("#name").val().trim().length == 0 || $("#name").val() == null){
        alert("菜名不能为空")
        return false;
    }
    if($("#uploadPic").val().length == 0 || $("#uploadPic").val() == null){
        alert("请选择图片")
        return false;
    }
    var n = /^[1-9]\d*$/;
    var regex = /^[1-9]\d*\.\d*|0\.\d*[1-9]\d*|0?\.0+|0$/;
    if (!regex.test(val) && !n.test(val))
    {
        alert("单价格式不正确！");
        return false;
    }
    return true;
}


function mySignin() {
    if($("#name").val().trim().length == 0 || $("#name").val() == null){
        alert("名字不为空");
        return false;
    }
    if($("#username").val().trim().length == 0 || $("#username").val() == null){
        alert("用户名不为空")
        return false;
    }
    if($("#pwd").val().trim().length == 0 || $("#pwd").val() == null){
        alert("密码不为空")
        return false;
    }
    if($("#confirmPwd").val().trim().length == 0 || $("#confirmPwd").val() == null){
        alert("确认密码不为空")
        return false;
    }
    if($("#dept").val() == 0){
        alert("请选择部门名")
        return false;
    }
    if($("#username").val().trim().length < 4 || $("#username").val().trim().length > 11){
        alert("用户名在4-11位之间")
        return false;
    }
    if($("#pwd").val().trim().length < 4 || $("#pwd").val().trim().length > 11){
        alert("密码在4-11位之间")
        return false;
    }
    if($("#confirmPwd").val().trim() != $("#pwd").val().trim()){
        alert("两次输入密码不一致")
        return false;
    }
    return true;
}

function myLoginin() {
    if($("#username").val().trim().length == 0 || $("#username").val() == null){
        alert("用户名不为空")
        return false;
    }
    if($("#pwd").val().trim().length == 0 || $("#pwd").val() == null){
        alert("密码不为空")
        return false;
    }
    if($("#username").val().trim().length < 4 || $("#username").val().trim().length > 11){
        alert("用户名在4-11位之间")
        return false;
    }
    if($("#pwd").val().trim().length < 4 || $("#pwd").val().trim().length > 11){
        alert("密码在4-11位之间")
        return false;
    }
    return true;
}