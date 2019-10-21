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
        async:"false",
        success: function (res) {

        }
    });
}

/**
 * 停止任务
 * 显示加载按钮
 * 传递任务id到后端，停止对应任务
 */
$('body').on("click",'.stop-spinner',function () {
    parent = $(this).parent().parent().parent();
    var taskId=$(".taskid").text()
    console.log(taskId)
    //显示加载按钮
    parent.children(".normal-cancel-spinner").css("display","block")
    //发送停止任务请求
    stopTask(taskId)
    parent.children(".normal-cancel-spinner").css("display","none")
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
$('body').on("click",'.cancel-spinner',function () {
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
