
function Validator(formSelector, booleanSubmit = true) {
	const _this = this;

	// tạo một đối tượng đưa ra các rules cho form
	var validatorRules = {
		isRequired: function(value) {
			return value ? undefined : 'Vui lòng nhập trường này'
		},
		isEmail: function(value) {
			var regex = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
			return regex.test(value) ? undefined : 'Vui lòng nhập đúng email'
		},
		min: function(min) {
			return function(value) {
				return value.length >= min ? undefined : `Vui lòng nhập ít nhất ${min} kí tự`;
			}
		},
		isPassword: function(value) {
			const pass = document.querySelector('input[name="password"]:not(disabled)').value;
			return pass === value ? undefined : 'Vui lòng nhập đúng mật khẩu xác nhận';
		},
		max: function(max) {
			return function(value) {
				return value.length <= max ? undefined : `Vui lòng nhập không vượt quá ${max} kí tự`;
			}
		}
	};
	// lấy cả form ra
	var formElement = document.querySelector(formSelector);
	// khi form tồn tại
	if (formElement) {
		var inputs = formElement.querySelectorAll('[name][rule]:not(disabled)');
		// tạo một object lấy name và rule của từng thẻ input
		var formRules = Array.from(inputs).reduce(function(callback, input) {
			var rules = input.getAttribute('rule').split('|'); // một mảng chứa các rule đối với một thẻ input
			for (var rule of rules) {
				var ruleHasMin = rule.includes(':');
				var ruleInfor;
				if (ruleHasMin) {
					ruleInfor = rule.split(':');
					rule = ruleInfor[0];
				}
				var ruleFuc = validatorRules[rule];

				if (ruleHasMin) {
					ruleFuc = ruleFuc(ruleInfor[1]);
				};

				if (Array.isArray(callback[input.name])) { // khi có mảng thì thêm function rule vào
					callback[input.name].push(ruleFuc);
				} else {
					callback[input.name] = [ruleFuc]; // tạo mảng 
				}
			}
			// hàm validate để có các sự kiện change, blur
			input.onblur = validate;
			input.oninput = handlerClearError;
			return callback
		}, {});

		function validate(event) {
			const input = event.target;
			var messageError;
			for (var i = 0; i < formRules[input.name].length; i++) {
				var arrRule = formRules[input.name][i];
				var parentInput = findParent(input, formNameSelector);
				switch (input.type) {
					case 'radio':
					case 'checkbox':
						var getValue;
						if (parentInput.querySelector('input[name]:checked:not(disabled)')) {
							getValue = parentInput.querySelector('input[name]:checked:not(disabled)').value;
						} else {
							getValue = '';
						}
						messageError = arrRule(getValue);
						break;
					default:
						messageError = arrRule(input.value);
				}
				if (parentInput) {
					var textError = parentInput.querySelector(formMessage);
					if (textError) {
						if (messageError) {
							parentInput.classList.add('invalid');
							textError.innerHTML = messageError;
							break;
						} else {
							parentInput.classList.remove('invalid');
							textError.innerHTML = '';
						}
					}
				}
			};
			return !messageError;
		}
		// hàm clear messError
		function handlerClearError(event) {
			const input = event.target;
			var messageError;
			var arrRule = formRules[input.name].find(function(rule) {
				messageError = rule(input.value);
				return rule;
			})
			// messageError = arrRule(input.value); // dư 

			var parentInput = findParent(event.target, formNameSelector);
			var textError = parentInput.querySelector(formMessage);
			if (parentInput.matches('.invalid')) {
				parentInput.classList.remove('invalid');
				if (!messageError) {
					textError.innerHTML = '';
				}
			}
		}

		function findParent(elementInput, selector) {
			while (elementInput) {
				if (elementInput.parentElement.matches(selector)) {
					return elementInput.parentElement;
				}
				elementInput = elementInput.parentElement;
			}
		}
	}
	formElement.onsubmit = function(e) {
		e.preventDefault();
		var inputs = formElement.querySelectorAll('[name][rule]:not(:disabled)');
		const formInvalid = document.querySelector(invalidForm);
		var isFormValid = true;
		for (var input of inputs) {
			var answer = validate({
				target: input,
			});
			if (!answer) {
				formInvalid.classList.add('form-invalid');
				isFormValid = false;
			}
		}
		// khi mà form hợp lệ
		if (isFormValid) {
			formInvalid.classList.remove('form-invalid');

			if (typeof _this.onSubmit === 'function') {
				var data = Array.from(inputs).reduce(function(callback, input) {
					switch (input.type) {
						case 'checkbox':
							if (!Array.isArray(callback[input.name])) {
								callback[input.name] = [];
							}
							if (input.matches(':checked')) {

								callback[input.name].push(input.value);
							}
							break;
						case 'radio':
							var valueChecked = formElement.querySelector('[name="' + input.name + '"]:checked');
							if (valueChecked) {
								callback[input.name] = valueChecked.value;
							} else {
								callback[input.name] = '';
							}
							break;
						case 'file':
							callback[input.name] = input.files;
							break;
						default:
							callback[input.name] = input.value;

					}
					return callback;
				}, {});
				//_this.onSubmit(data);

			} else {
				var btnSubmit = document.querySelector('.btn-submit-js')
				var form = document.querySelector('#form-1')

//////////////////////////
					var viewContent = document.querySelector('.view-content');

    				if (btnSubmit) {
    					btnSubmit.onclick = function(e) {

    						var firstName = form.querySelector('[name="firstname"]:not(disabled)').value;
    						var lastName = form.querySelector('[name="lastname"]:not(disabled)').value;
    						var email = form.querySelector('[name="email"]:not(disabled)').value;
    						var phone = form.querySelector('[name="phone"]:not(disabled)').value;
    						var descr = form.querySelector('[name="description"]:not(disabled)').value;
    						var model = {
    							firstname: firstName.trim(),
    							lastname: lastName.trim(),
    							email: email.trim(),
    							phone: phone.trim(),
    							description: descr.trim()
    						};
    							
    							
    						$.ajax({
    							url: '/ProjectJava/profilecontroller',
    							type: 'POST',
    							data: model,
    							success: function(data) {

    								$('.main-app').load('/ProjectJava/profilecontroller');

    								alert("Cập nhật thành công");

    							},
    							error: function(data, errorThrown) {
    								alert(errorThrown);
    							}
    						});

    					}


				}
				if(booleanSubmit){
					formElement.submit();
				}
				

			}
		}
	}

	var accountUser = document.querySelector('.header__account');

	var app = document.querySelector('.main-app');


		accountUser.onclick = (event) => {
			event.stopPropagation();
			accountUser.style.backgroundColor = "#EEEEEE";
			accountUser.classList.add('header__account--appear');

		};
	
	

		app.onclick = () => {
			accountUser.style.backgroundColor = "";
			accountUser.classList.remove('header__account--appear');
		};
	

	/*$("body").on("click", ".page", function() {
		var numPage = $(this).text();
		$.ajax({
			url: '/ProjectJava/content',
			type: 'POST',
			data: {
				currentPage: numPage
			},
			suscess: function(data) {
				alert(data);
			}
		});
	})*/



}