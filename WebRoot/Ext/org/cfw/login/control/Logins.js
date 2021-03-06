Ext.onReady(function() {
	Ext.QuickTips.init();
	Ext.form.Field.prototype.msgTarget = 'side';
	var loginWindow = new cfw.login.view.Login();
	loginWindow.show();

	init();
});

function init() {
	var loginButton = Ext.getCmp("loginButton");
	loginButton.on('click', loginUser);

	var resetButton = Ext.getCmp("resetButton");
	resetButton.on('click', resetUser);

	var form = Ext.getCmp("form");
	var nav = new Ext.util.KeyNav(form.getEl(), {
		"enter" : function(e) {
			loginUser();
		},
		scope : this
	});
}

function loginUser() {
	var form = Ext.getCmp("form");
	var checkcode = Ext.getCmp("checkcode");
	setErrinfo("");
	if (form.getForm().isValid()) {
		form.getForm().submit({
			method : "post",
			url : "login.action",
			waitMsg : "请稍等，正在登录...",
			waitTitle : "正在验证",
			success : function(form, action) {
				if (currentTheme == 'ext-all') {
					window.location.href = 'index.action';
				} else if (currentTheme == 'ext-all-gray') {
					window.location.href = 'index_gray.action';
				} else if (currentTheme == 'ext-all-access') {
					window.location.href = 'index_access.action';
				} else {
					window.location.href = 'index.action';
				}
			},
			failure : function(form, action) {
				// resetUser();
				setErrinfo('登录失败:' + action.result.msg);
				checkcode.loadCodeImg();
				switch (action.result.flag) {
				case 1:
					checkcode.reset();
					checkcode.focus();
					break;
				case 2:
					var username = Ext.getCmp("username");
					username.reset();
					username.focus();
					break;
				case 3:
					var password = Ext.getCmp("password");
					password.reset();
					password.focus();
					break;
				}
			}
		})
	}

}

function setErrinfo(errMsg) {
	var errinfo = Ext.getCmp("errinfoLabel");
	errinfo.setText(errMsg);
}

function resetUser() {
	var form = Ext.getCmp("form");
	form.getForm().reset();
}
