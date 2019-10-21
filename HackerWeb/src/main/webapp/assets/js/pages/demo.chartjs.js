// var ctx = document.getElementById("radar-chart-example");
// var ctx0 = document.getElementById("donut-chart-example");
//
// var circle = new Chart(ctx0, {
//     type: 'doughnut'// or 'pie'
//     , data: {
//         datasets: [{
//             data: [300, 135, 48, 154, 50, 600],
//             backgroundColor: ["#727cf5", "#fa5c7c", "#0acf97", "#ebeff2", "#00EE00", "#20B2AA"],
//             borderColor: "transparent",
//             borderWidth: "3"
//         }],
//         labels: ["暴恐", "反动", "色情", "其他", "民生", "自定义"]
//     },
// });
//
// var leida = new Chart(ctx, {
//     type: 'radar'
//     , data: {
//         labels: ["暴恐敏感词", "反动敏感词", "色情敏感词", "其他敏感词", "民生敏感词", "自定义敏感词"],
//         datasets: [{
//             label: "敏感词",
//             backgroundColor: "rgba(57,175,209,0.2)",
//             borderColor: "#39afd1",
//             pointBackgroundColor: "#39afd1",
//             pointBorderColor: "#fff",
//             pointHoverBackgroundColor: "#fff",
//             pointHoverBorderColor: "#39afd1",
//             data: [200, 59, 90, 81, 56, 55]
//         }]
//     },
//     options: {
//         // 需要特别注意的是: 这里的scale没有s
//         scale: {
//             ticks: {
//                 min: 0
//             }
//         }
//     }
// });

/**
 *
 * @constructor
 */
function AddSensitiveWordTasks(){
    //创建中过渡区域
    var temphtml =
        "                                    <div id='temp-card' class=\"card mb-0\">\n" +
        "                                    <div class=\"card-body p-3\">\n" +
        "                                        <div class=\"d-flex align-items-center text-success m-2\">\n" +
        "                                            <strong>正在创建任务，请稍后</strong>\n" +
        "                                            <div class=\"spinner-grow ml-auto\" role=\"status\" aria-hidden=\"true\"></div>\n" +
        "                                        </div>\n" +
        "                                    </div>\n" +
        "                                    </div>";

    //第一次创建不包含间隔
    let card = document.getElementsByClassName("card")
    if (card.length!=0){
        temphtml = "<br><br>\n" +temphtml;
    }
    $('#content').append(temphtml);
    submitCheck()
}


function submitCheck() {
    //显示加载按钮
    document.getElementById("loading").style.display = "";
    //隐藏查询按钮
    document.getElementById("querybutton").style.display = "none";
    var url = $('#curl').val()

    $.ajax({
        type: "post",
        url: "SensitiveWord",
        data: {"url": url},
        dataType: "json",
        success: function (res) {
            console.log(res)
            //显示查询按钮
            document.getElementById("querybutton").style.display = "";
            //隐藏加载按钮
            document.getElementById("loading").style.display = "none";
            if (null!=res){
                //结果HTML
                var taskHtml = "<div class=\"card mb-0\"><div class=\"card-body p-3\">\n" +
                    "                                                <small class=\"float-right text-muted\"><font style=\"vertical-align: inherit;\"><font style=\"vertical-align: inherit;\">"+res.starttime+"</font></font></small>\n" +
                    "                                                <span class=\"badge badge-success\"><font style=\"vertical-align: inherit;\"><font style=\"vertical-align: inherit;\">运行中</font></font></span>\n" +
                    "                                                <h5 class=\"mt-2 mb-2\">\n" +
                    "                                                    <a href=\"#\" data-toggle=\"modal\" data-target=\"#task-detail-modal\" class=\"text-body\"><font style=\"vertical-align: inherit;\"><font style=\"vertical-align: inherit;\">"+res.type+"</font></font></a>\n" +
                    "                                                </h5>\n" +
                    "                                                <p class=\"mb-0\">\n" +
                    "                                                    <span class=\"pr-2 text-nowrap mb-2 d-inline-block\">\n" +
                    "                                                        <i class=\"mdi mdi-briefcase-outline text-muted\"></i><font style=\"vertical-align: inherit;\"><span style=\"vertical-align: inherit;\">\n" +
                    "                                                       <strong>任务id:</strong><span class='taskid'>"+ res.taskid+
                    "                                                    </span></font></font></span>\n" +
                    "                                                </p>\n" +
                    "                                                <div class=\"dropdown float-right\">\n" +
                    "                                                    <a href=\"#\" class=\"dropdown-toggle text-muted arrow-none\" data-toggle=\"dropdown\" aria-expanded=\"false\">\n" +
                    "                                                        <i class=\"mdi mdi-dots-vertical font-18\"></i>\n" +
                    "                                                    </a>\n" +
                    "                                                    <div class=\"dropdown-menu dropdown-menu-right\">\n" +
                    "                                                        <!-- item-->\n" +
                    "                                                        <a href=\"javascript:void(0);\"   class=\"dropdown-item stop-spinner\"><i class=\"mdi mdi-pencil mr-1\"></i>&nbsp;&nbsp;&nbsp;&nbsp;停止</a>\n" +
                    "                                                        <!-- item-->\n" +
                    "                                                        <a href=\"javascript:void(0);\"  class=\"dropdown-item cancel-spinner\"><i class=\"mdi mdi-delete mr-1\"></i>删除</a>\n" +
                    "                                                    </div>\n" +
                    "                                                </div>\n" +
                    "\n" +
                    "                                                <p class=\"mb-0\">\n" +
                    "                                                    <!-- <img src=\"assets/images/users/avatar-2.jpg\" alt=\"用户IMG\" class=\"avatar-xs rounded-circle mr-1\"> -->\n" +
                    "                                                    <span class=\"align-middle\"><font style=\"vertical-align: inherit;\"><font style=\"vertical-align: inherit;\">"+res.taskurl+"</font></font></span>\n" +
                    "                                                </p>\n" +
                    "                                                <div class=\"normal-cancel-spinner\" id=\"stop-spinner\" style=\"display: none\">\n" +
                    "                                                    <hr>\n" +
                    "                                                    <div class=\"d-flex align-items-center text-secondary m-2\">\n" +
                    "                                                        <strong>取消中...</strong>\n" +
                    "                                                        <div class=\"spinner-grow ml-auto\" role=\"status\" aria-hidden=\"true\"></div>\n" +
                    "                                                    </div>\n" +
                    "                                                </div>\n" +
                    "                                            </div></div>";

                $("div").remove("#temp-card")
                $('#content').append(taskHtml)
            }
        }
    });
}



