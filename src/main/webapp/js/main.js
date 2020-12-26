jQuery(document).ready(function() {
    //涓嬫媺妗嗘帶浠�
    try{
        jQuery('.selectpicker').selectpicker('val',jQuery('.selectpicker').attr('value'));
    }catch(e){}
    //鏃堕棿鎺т欢
    try{
        jQuery('input.datepicker').each(function(){
            var fmt=$(this).attr('data-date-format');
            fmt=fmt||'yyyy-mm-dd';
            var cfg={
                language:'zh-CN',
                autoclose:true,
                format:fmt,
                clearBtn:true};
            if(fmt.indexOf('mm')>-1&&fmt.indexOf('dd')==-1){
                cfg.startView=1;
                cfg.minViewMode=1;
            }else if(fmt=='yyyy'){
                cfg.startView=2;
                cfg.maxViewMode=2;
                cfg.minViewMode=2;
            }
            $(this).datepicker(cfg);
        }).on('hide',function(){
            jQuery(this).blur();
        });
        jQuery('input.datepicker').click(function(){
            jQuery(this).datepicker('show');

            var b=jQuery(this).val().length;
            if(!b){
                return;
            }
            var o=this;
            if(o.createTextRange){
                var range = o.createTextRange();
                range.moveStart("character", 0);
                range.moveEnd("character",b);
                range.select();
            }else{
                o.setSelectionRange(0, b);
                o.focus();
            }
        }).focus(function() {
            var input = jQuery(this);
            if (input.val() == input.attr('placeholder')) {
                input.val('');
                input.removeClass('placeholder');
            }
        }).blur(function() {
            var input = jQuery(this);
            if (input.val() == '' || input.val() == input.attr('placeholder')) {
                input.addClass('placeholder');
                input.val(input.attr('placeholder'));
            }
        }).blur();
    }catch(e){}
    try{
        //iCheck鎺т欢
        jQuery('.cbxs input').iCheck({
            checkboxClass: 'icheckbox_square-red',
            radioClass: 'iradio_square-red',
            increaseArea: '20%'
        });
    }catch(e){}
    try{
        //鑷畾涔塺adio鎺т欢
        jQuery('.radioable').radio();
    }catch(e){}

    jQuery('.clickable').click(function(){
        if(jQuery(this).hasClass('active')){
            jQuery(this).removeClass('active');
            jQuery(this).find('input').attr('checked',false);
        }else{
            jQuery(this).addClass('active');
            jQuery(this).find('input').attr('checked',true);
        }
    });
    /**鍥炴樉涓氫綑鏃堕棿**/
    jQuery('.table-row').each(function(i,dom){
        var vals=$(this).attr('value');
        if(vals){
            $(this).find('.clickable').each(function(){
                var val=$(this).attr('value');
                if(vals.indexOf(val)!=-1){
                    $(this).click();
                }
            });
        }
    });
    //checkbox鍏ㄩ€夈€佸弽閫�
    jQuery('.cbxall .iCheck-helper').hover(function(){
        jQuery(this).parents('.cbxall').addClass('hover');
    },function(){
        jQuery(this).parents('.cbxall').removeClass('hover');
    }).click(function(){
        var cbx=jQuery(this).parents('.cbxall');
        var ck=false;
        if(cbx.hasClass('checked')){
            cbx.removeClass('checked');
            ck=false;
        }else{
            cbx.addClass('checked');
            ck=true;
        }
        jQuery('.cbxsingle .iCheck-helper').trigger('click',ck);
    });
    jQuery('.cbxsingle .iCheck-helper').hover(function(){
        jQuery(this).parents('.cbxsingle').addClass('hover');
    },function(){
        jQuery(this).parents('.cbxsingle').removeClass('hover');
    }).click(function(e,k){
        var cbx=jQuery(this).parents('.cbxsingle');
        if(typeof k=='undefined'){
            if(cbx.hasClass('checked')){
                cbx.removeClass('checked');
            }else{
                cbx.addClass('checked');
            }
        }else{
            if(k){
                cbx.addClass('checked');
            }else{
                cbx.removeClass('checked');
            }
        }
    });
    /**msgbox**/
    jQuery('body').on('click','.msgbox .closes',function(){
        jQuery(this).parents('.msgbox').fadeOut();
    });
    /**form琛ㄥ崟楠岃瘉涓€у寲淇敼锛岃姹俧orm蹇呴』甯lass锛歷alidable**/
    jQuery('.validable').on('error.field.bv', function(e, data) {
        data.element.addClass('field-error');
        var tmp=data.element.parents('.has-error').find('.help-block').remove();
        if(data.element.siblings('.help-block').size()<1){
            data.element.parent().append(tmp);
        }
        var tg=data.element.siblings('.help-block:visible');

        if(tg.size()<1){
            tg=data.element.siblings('.help-block[data-bv-result="INVALID"]').show();
        }

        tg.each(function(i,dom){
            if(jQuery(dom).find('.arrow').size()<1){
                jQuery(dom).prepend('<span class="icon ui-element"></span>');
                jQuery(dom).prepend('<span class="arrow ui-element"></span>');
            }
            if(i>0){
                jQuery(dom).hide();
            }
        });

        jQuery(this).find('.help-block:visible').each(function(i,dom){
            if(i>0){
                jQuery(dom).hide();
                data.element.removeClass('field-error');
            }
        });

    }).on('success.field.bv', function(e, data) {
        data.element.removeClass('field-error');
        //$(".has-error:visible:first").find(":input").focus();
    });
    jQuery('.navbar-toggle').click( function() {
        jQuery('body').addClass('modal-open');
        jQuery('#site-access').addClass('accessIn');
    });
    jQuery('.close-access').click( function() {
        jQuery('body').removeClass('modal-open');
        jQuery('#site-access').removeClass('accessIn');
    });
    jQuery('.city-switcher').click( function() {
        jQuery('#site-access').removeClass('accessIn');
    });
    jQuery('.submenu-icon').click( function() {
        jQuery(this).parents('#about-toolbar').find('.about-nav').addClass('is-open');
    });
    $(".submenu-icon").click(function(e) {
        e?e.stopPropagation():event.cancelBubble = true;
    });
    $(document).click(function() {
        $(this).find('.about-nav').removeClass('is-open');
    });
    jQuery('.accountsub-icon').click( function() {
        jQuery(this).parents('.account-mark').find('.account-tab').addClass('is-open');
    });



    jQuery('.user-switcher').click( function() {
        jQuery('body').addClass('modal-open');
        jQuery('#user-panel').addClass('panel-in');
    });
    jQuery('.close-user').click( function() {
        jQuery('body').removeClass('modal-open');
        jQuery('#user-panel').removeClass('panel-in');
    });


    if( jQuery(window).width() < 768 ) {
        var imagerwidth = jQuery('.imager').width();
        jQuery('.imager').css({'height':imagerwidth+'px'});
    } else {
        jQuery('.imager').css({'height':'600px'});
    }
    jQuery(window).bind('scroll resize', function() {
        if( jQuery(window).width() < 768 ) {
            var imagerwidth = jQuery('.imager').width();
            jQuery('.imager').css({'height':imagerwidth+'px'});

            var st=$(window).scrollTop();
            var fh=$('#footer').height();
            var hh=$('#header').height();
            var ft=$('#footer').offset().top;
            var tmp=ft-hh-fh-200;
            if(st>=tmp){
                $('.btn').parent('.msg-top-button').fadeOut(300);
                $('.btn').parent('.withpad').fadeOut(300);
            }else{
                $('.btn').parent('.msg-top-button').fadeIn(300);
                $('.btn').parent('.withpad').fadeIn(300);
            }
        } else {
            jQuery('.imager').css({'height':'600px'});
        }

    });


});