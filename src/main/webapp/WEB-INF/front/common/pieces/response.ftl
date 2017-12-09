	  <div class="title" id="comment">
        <h3>评论</h3> <a style="float: right;font-size: 12px;margin-top: 5px;" href="javascript:void(0);" onclick="$('#respond').toggle(500);">发表评论>></a>
      </div>
      <div id="respond" style="display:none">
                <div class="comment">
                    <input name="articlescrap_id" value="${articlescrap.id}" type="hidden">
                    <input name="comment_name" id="comment_name" class="form-control" size="22" placeholder="昵称（必填）" maxlength="15" autocomplete="off" tabindex="1" type="text">
                    <input name="email" id="" class="form-control" size="22" placeholder="网址或邮箱（非必填）" maxlength="58" autocomplete="off" tabindex="2" type="text">
                    <div class="comment-box">
                        <textarea placeholder="评论或留言（必填）" name="comment" id="comment-textarea" cols="100%" rows="3" tabindex="3"></textarea>
                        <div class="comment-ctrl">
                            <div class="comment-prompt" style="display: none;"> <i class="fa fa-spin fa-circle-o-notch"></i> <span class="comment-prompt-text">评论正在提交中...请稍后</span> </div>
                            <div class="comment-success" style="display: none;"> <i class="fa fa-check"></i> <span class="comment-prompt-text">评论提交成功...</span> </div>
                            <button type="button" name="comment-submit" id="comment-submit" tabindex="4">评论</button>
                        </div>
                    </div>
                </div>
        </div>
      <div id="postcomments">
        <ol id="comment_list" class="commentlist">
        <#list commentsPageinfo.objects as comment>
        	<li class="comment-content"><span class="comment-f">${comment_index+1}楼</span><div class="comment-main"><p><a class="address" href="javascript:void(0);" rel="nofollow">${comment.comment_name}</a><span class="time">(${comment.comment_time?datetime})</span><br>${comment.comment}</p></div></li>
        </#list>
      </div>
