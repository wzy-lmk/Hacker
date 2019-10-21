function getTaskList(type) {
    $.ajax({
        type: "post",
        url: "TaskInfo",
        data: {"type":type},
        dataType: "json",
        async:"true",
        success:function (res) {
            console.log(res)
            console.log(res.length)
            for (let i=0;i<res.length;i++){
                //结果HTML
                var taskHtml = "<br><br><div class=\"card mb-0\"><div class=\"card-body p-3\">\n" +
                    "                                                <small class=\"float-right text-muted\"><font style=\"vertical-align: inherit;\"><font style=\"vertical-align: inherit;\">"+res[i].starttime+"</font></font></small>\n" +
                    "                                                <span class=\"badge badge-success\"><font style=\"vertical-align: inherit;\"><font style=\"vertical-align: inherit;\">运行中</font></font></span>\n" +
                    "                                                <h5 class=\"mt-2 mb-2\">\n" +
                    "                                                    <a href=\"#\" data-toggle=\"modal\" data-target=\"#task-detail-modal\" class=\"text-body\"><font style=\"vertical-align: inherit;\"><font style=\"vertical-align: inherit;\">"+res[i].type+"</font></font></a>\n" +
                    "                                                </h5>\n" +
                    "                                                <p class=\"mb-0\">\n" +
                    "                                                    <span class=\"pr-2 text-nowrap mb-2 d-inline-block\">\n" +
                    "                                                        <i class=\"mdi mdi-briefcase-outline text-muted\"></i><font style=\"vertical-align: inherit;\"><span style=\"vertical-align: inherit;\">\n" +
                    "                                                       <strong>任务id:</strong><span class='taskid'>"+ res[i].taskid+
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
                    "                                                    <span class=\"align-middle\"><font style=\"vertical-align: inherit;\"><font style=\"vertical-align: inherit;\">"+res[i].taskurl+"</font></font></span>\n" +
                    "                                                </p>\n" +
                    "                                                <div class=\"normal-cancel-spinner\" id=\"stop-spinner\" style=\"display: none\">\n" +
                    "                                                    <hr>\n" +
                    "                                                    <div class=\"d-flex align-items-center text-secondary m-2\">\n" +
                    "                                                        <strong>取消中...</strong>\n" +
                    "                                                        <div class=\"spinner-grow ml-auto\" role=\"status\" aria-hidden=\"true\"></div>\n" +
                    "                                                    </div>\n" +
                    "                                                </div>\n" +
                    "                                            </div></div>";

                $('#content').append(taskHtml)
            }
        },error: function(res){
            console.log(res)
            alert(arguments[1]);
        }
    });
}