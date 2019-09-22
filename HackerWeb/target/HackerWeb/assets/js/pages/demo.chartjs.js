var ctx=document.getElementById("radar-chart-example");
var ctx0=document.getElementById("donut-chart-example");

var circle =new Chart(ctx0, {
    type: 'doughnut'// or 'pie'
    ,data: {
        datasets: [{
            data: [300, 135, 48, 154,50,600],
            backgroundColor: ["#727cf5", "#fa5c7c", "#0acf97", "#ebeff2","#00EE00","#20B2AA"],
            borderColor: "transparent",
            borderWidth: "3"
        }],
        labels: ["暴恐", "反动", "色情", "其他","民生","自定义"]
    },
});

var leida=new Chart(ctx, {
    type: 'radar'
    ,data:{
        labels: ["暴恐敏感词", "反动敏感词", "色情敏感词", "其他敏感词","民生敏感词","自定义敏感词"],
        datasets: [{
            label: "敏感词",
            backgroundColor: "rgba(57,175,209,0.2)",
            borderColor: "#39afd1",
            pointBackgroundColor: "#39afd1",
            pointBorderColor: "#fff",
            pointHoverBackgroundColor: "#fff",
            pointHoverBorderColor: "#39afd1",
            data: [200, 59, 90, 81, 56, 55]
        }]
    },
    options: {
        // 需要特别注意的是: 这里的scale没有s
        scale: {
            ticks: {
                min: 0
            }
        }
    }
});


function submitCheck() {

    //禁用查询按钮
    $("#submit-minganci").attr("disable","true")

    //不显示图表
    document.getElementById("result-chart").style.display="none";

    //获取查询内容
    var choose= $('#wordschoose').val().toString()
    var content=$('#summernote-editmode').summernote('code')
    var url = $('#curl').val()

    $.ajax({
        type: "post",
        url: "SensitiveWord",
        data: {"choose":choose,"content":content,"url":url},
        dataType: "json",
        success: function (res) {
            if (res.result=="fail") {
                $.NotificationApp.send("查询失败", "输入的url不正确，或该站点禁制爬虫访问。", "top-center","rgba(0,0,0,0.2)","fail")
            }else{
                var data=res.toString().split(",")
                var nums =new Array(6);
               for (var i=0;i<data.length;i++){
                   nums[i]=parseInt(data[i]);
               }
                console.log(nums.type)
                document.getElementById("result-chart").style.display="";
                circle.data.datasets[0].data=nums;
                leida.data.datasets[0].data=nums;
                circle.update();
                leida.update();
            }
            $("#submit-minganci").attr("disable","false")
        }
    });
}



