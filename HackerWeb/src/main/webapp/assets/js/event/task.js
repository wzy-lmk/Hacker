/**
 * 停止任务
 * @param taskId 任务id
 */
function stopTask(taskId){
    $.ajax({
        type: "post",
        url: "StopTask",
        data: {"taskId":taskId},
        dataType: "json",
        async:true, //异步等待
        success: function (res) {
            //关闭加载图片
            document.getElementById("stop-spinner").style.display="none"
        }
    });
}

/**
 * 创建新任务
 * @constructor
 */
function AddTasks(){

    var name = $('#task-description').val()
    var url = $('#task-url').val()
    var hight=$('#task-hight option:selected').val()

    //创建中过渡区域
    var temphtml =
        "                                    <div class=\"card mb-0\">\n" +
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

    //结果HTML
    var taskHtml = "<div class=\"card-body p-3\">\n" +
        "                                                <small class=\"float-right text-muted\"><font style=\"vertical-align: inherit;\"><font style=\"vertical-align: inherit;\">2019年9月23日</font></font></small>\n" +
        "                                                <span class=\"badge badge-success\"><font style=\"vertical-align: inherit;\"><font style=\"vertical-align: inherit;\">运行中</font></font></span>\n" +
        "                                                <h5 class=\"mt-2 mb-2\">\n" +
        "                                                    <a href=\"#\" data-toggle=\"modal\" data-target=\"#task-detail-modal\" class=\"text-body\"><font style=\"vertical-align: inherit;\"><font style=\"vertical-align: inherit;\">检测网页图片更改</font></font></a>\n" +
        "                                                </h5>\n" +
        "                                                <p class=\"mb-0\">\n" +
        "                                                    <span class=\"pr-2 text-nowrap mb-2 d-inline-block\">\n" +
        "                                                        <i class=\"mdi mdi-briefcase-outline text-muted\"></i><font style=\"vertical-align: inherit;\"><font style=\"vertical-align: inherit;\">\n" +
        "                                                       <strong>任务id:</strong>184654654\n" +
        "                                                    </font></font></span>\n" +
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
        "                                                    <span class=\"align-middle\"><font style=\"vertical-align: inherit;\"><font style=\"vertical-align: inherit;\">http://www.douban.com</font></font></span>\n" +
        "                                                </p>\n" +
        "                                                <div class=\"normal-cancel-spinner\" style=\"display: none\">\n" +
        "                                                    <hr>\n" +
        "                                                    <div class=\"d-flex align-items-center text-secondary m-2\">\n" +
        "                                                        <strong>取消中...</strong>\n" +
        "                                                        <div class=\"spinner-grow ml-auto\" role=\"status\" aria-hidden=\"true\"></div>\n" +
        "                                                    </div>\n" +
        "                                                </div>\n" +
        "                                            </div>";

    $('#content').append(temphtml);
}

/**
 * 停止任务
 * 显示加载按钮
 * 传递任务id到后端，停止对应任务
 */
$('.stop-spinner').on("click",function (taskId) {
    parent = $(this).parent().parent().parent();
    //显示加载按钮
    parent.children(".normal-cancel-spinner").css("display","block")
    //发送停止任务请求
    stopTask()
    //完成后显示
    parent.children(".badge").removeClass("badge-success")
    parent.children(".badge").removeClass("badge-success").addClass("badge-secondary")
    parent.children(".badge").html("<font style=\"vertical-align: inherit;\">已停止</font>");

})

/**
 * 删除任务
 * 显示确认弹窗
 * 移除任务区域
 * 发送删除请求
 */
$('.cancel-spinner').on("click",function () {
    let tass = $(this)
    $('#info-alert-modal').modal('show')
    $('#cancel-button').click(function () {
        tass.parent().parent().parent().parent()[0].remove()
    })
})

/***
 * 创建任务
 * 发送创建任务请求
 * 隐藏任务模态窗口
 */
$('#createTask').on("click",function () {
    $('#add-new-task-modal').modal('hide')
    AddTasks()
})
