window.onload=function()
{
    getResult("");
    var date=new Date;
    var year=date.getFullYear();
    var content = "<option value=''>全选</option>";
    for(var i=year-5;i<=year;i++)
    {
        if(i==year)
        {
            content += "<option value="+i+" selected='selected'>"+i+"</option>";
        }
        else
        {
            content += "<option value="+i+">"+i+"</option>";
        }
    }
    $("#year").append(content);
    showMonth();
}

function getDaysInMonth(year,month){
    month = parseInt(month,10); //parseInt(number,type)这个函数后面如果不跟第2个参数来表示进制的话，默认是10进制。
    var temp = new Date(year,month,0);
    return temp.getDate();
}

function showDay()
{
    $("#day").html("");
    var year = $("#year").val();
    var month = $("#month").val();
    if(month == "")
    {
        return;
    }
    var end = getDaysInMonth(year,month);
    var content = "<option value='' selected='selected'>全选</option>";
    for(var i=1;i<=end;i++)
    {
        content += "<option value="+i+">"+i+"</option>"
    }
    $("#day").append(content)
}
function show()
{
    if($("#year").val()=="")
    {
        $("#day").html("");
        $("#month").html("");
    }
    else
    {
        $("#day").html("");
        $("#month").html("");
        showMonth();
    }
}

function showMonth()
{
    var content0 = "<option value='' selected='selected'>全选</option>";
    for(var j=1;j<=12;j++)
    {
        content0 += "<option value="+j+">"+j+"</option>";
    }
    $("#month").append(content0);
}

function query()
{
    var date = $("#year").val();
    if($("#month").val() != ""&&$("#month").val() != null)
    {
        if(parseInt($("#month").val())<10)
        {
            date += "-0"+$("#month").val();
        }
        else
        {
            date += "-"+$("#month").val();
        }
    }
    if($("#day").val() != ""&&$("#day").val() != null)
    {
        if(parseInt($("#day").val())<10)
        {
            date += "-0"+$("#day").val();
        }
        else
        {
            date += "-"+$("#day").val();
        }
    }
    return date;
}

