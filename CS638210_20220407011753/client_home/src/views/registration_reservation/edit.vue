<template>
	<div class="diy_edit page_registration_reservation" id="registration_reservation_edit">
		<div class='warp'>
			<div class='container'>
				<div class='row diy_edit_content_box'>
					<div v-if="$check_field('set','reservation_number') || $check_field('add','reservation_number') || $check_field('get','reservation_number')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>预约编号:
							</span>
						</div>
						<!-- 文本 -->
						<div class="diy_field diy_text">
							<input type="text" id="form_reservation_number" v-model="form['reservation_number']" placeholder="请输入预约编号" v-if="(form['reservation_number'] && $check_field('set','reservation_number')) || (!form['reservation_number'] && $check_field('add','reservation_number'))" :disabled="true"/>
							<span v-else-if="$check_field('get','reservation_number')">{{ form['name_of_doctor'] }}</span>
						</div>
					</div>
					<div v-if="$check_field('set','doctor_number') || $check_field('add','doctor_number') || $check_field('get','doctor_number')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>医生编号:
							</span>
						</div>
						<div class="diy_field diy_down">
							<select id="form_doctor_number" :disabled="disabledObj['doctor_number_isDisabled']" v-model="form['doctor_number']" v-if="(form['doctor_number'] && $check_field('set','doctor_number')) || (!form['doctor_number'] && $check_field('add','doctor_number'))" >
								<option v-for="o in list_user_doctor_number" :value="o['user_id']">
									{{o['nickname'] + '-' + o['username']}}
								</option>
							</select>
							<span v-else-if="$check_field('get','doctor_number')">{{ form['doctor_number'] }}</span>
						</div>
					</div>
					<div v-if="$check_field('set','name_of_doctor') || $check_field('add','name_of_doctor') || $check_field('get','name_of_doctor')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>医生姓名:
							</span>
						</div>
						<!-- 文本 -->
						<div class="diy_field diy_text">
							<input type="text" id="form_name_of_doctor" v-model="form['name_of_doctor']" placeholder="请输入医生姓名" v-if="(form['name_of_doctor'] && $check_field('set','name_of_doctor')) || (!form['name_of_doctor'] && $check_field('add','name_of_doctor'))"  :disabled="disabledObj['name_of_doctor_isDisabled']"/>
							<span v-else-if="$check_field('get','name_of_doctor')">{{ form['name_of_doctor'] }}</span>
						</div>
					</div>
					<div v-if="$check_field('set','department_name') || $check_field('add','department_name') || $check_field('get','department_name')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>科室名称:
							</span>
						</div>
						<!-- 文本 -->
						<div class="diy_field diy_text">
							<input type="text" id="form_department_name" v-model="form['department_name']" placeholder="请输入科室名称" v-if="(form['department_name'] && $check_field('set','department_name')) || (!form['department_name'] && $check_field('add','department_name'))"  :disabled="disabledObj['department_name_isDisabled']"/>
							<span v-else-if="$check_field('get','department_name')">{{ form['name_of_doctor'] }}</span>
						</div>
					</div>
					<div v-if="$check_field('set','patient') || $check_field('add','patient') || $check_field('get','patient')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>患者:
							</span>
						</div>
						<div class="diy_field diy_down">
							<select id="form_patient" :disabled="disabledObj['patient_isDisabled']" v-model="form['patient']" v-if="(form['patient'] && $check_field('set','patient')) || (!form['patient'] && $check_field('add','patient'))" >
								<option v-for="o in list_user_patient" :value="o['user_id']">
									{{o['nickname'] + '-' + o['username']}}
								</option>
							</select>
							<span v-else-if="$check_field('get','patient')">{{ form['patient'] }}</span>
						</div>
					</div>
					<div v-if="$check_field('set','full_name') || $check_field('add','full_name') || $check_field('get','full_name')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>姓名:
							</span>
						</div>
						<!-- 文本 -->
						<div class="diy_field diy_text">
							<input type="text" id="form_full_name" v-model="form['full_name']" placeholder="请输入姓名" v-if="(form['full_name'] && $check_field('set','full_name')) || (!form['full_name'] && $check_field('add','full_name'))"  :disabled="disabledObj['full_name_isDisabled']"/>
							<span v-else-if="$check_field('get','full_name')">{{ form['name_of_doctor'] }}</span>
						</div>
					</div>
					<div v-if="$check_field('set','time_of_appointment') || $check_field('add','time_of_appointment') || $check_field('get','time_of_appointment')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>预约时间:
							</span>
						</div>
						<!-- 日期 -->
						<div class="diy_field diy_date">
							<input type="date" :disabled="disabledObj['time_of_appointment_isDisabled']" id="form_time_of_appointment" v-model="form['time_of_appointment']" placeholder="请输入预约时间" v-if="(form['time_of_appointment'] && $check_field('set','time_of_appointment')) || (!form['time_of_appointment'] && $check_field('add','time_of_appointment'))" />
							<span v-else-if="$check_field('get','time_of_appointment')">{{ form['name_of_doctor'] }}</span>
						</div>
					</div>
					<div v-if="user_group === '管理员' || $check_examine()" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>
								审核状态:
							</span>
						</div>
						<div class="diy_field diy_select" v-if="$check_action('/registration_reservation/edit','examine')">
							<!--<span> {{ form['examine_state'] }} </span>-->
							<select v-model="form['examine_state']">
								<option value="未审核">
									未审核
								</option>
								<option value="已通过">
									已通过
								</option>
								<option value="未通过">
									未通过
								</option>
							</select>
						</div>
						<div class="diy_field diy_text" v-else>
							<span>
								{{ form['examine_state'] }}
							</span>
						</div>
					</div>
					<div v-if="user_group === '管理员' || $check_examine()" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>
								审核回复:
							</span>
						</div>
						<div class="diy_field diy_desc" v-if="$check_action('/registration_reservation/edit','examine')">
							<textarea v-model="form['examine_reply']"></textarea>
						</div>
						<div class="diy_field diy_text" v-else>
							<span>
								{{ form['examine_reply'] }}
							</span>
						</div>
					</div>
				</div>
				<div class="diy_edit_submit_box row">
					<div class="col-12">
						<div class="btn_box">
							<button class="btn_submit" @click="submit()">提交</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
	import mixin from "@/mixins/page.js";
	export default {
		mixins: [mixin],
		components: {},
		data() {
			return {
				url_get_obj: "~/api/registration_reservation/get_obj?",
				url_add: "~/api/registration_reservation/add?",
				url_set: "~/api/registration_reservation/set?",

				// 登录权限
				oauth: {
					"signIn": true,
					"user_group": []
				},

				// 查询条件
				query: {
					"reservation_number": "",
					"doctor_number": 0,
					"name_of_doctor": "",
					"department_name": "",
					"patient": 0,
					"full_name": "",
					"time_of_appointment": "",
					"registration_reservation_id": 0,
				},

				obj: {
					"reservation_number":this.$get_stamp(), // 预约编号
					"doctor_number": 0, // 医生编号
					"name_of_doctor":'', // 医生姓名
					"department_name":'', // 科室名称
					"patient": 0, // 患者
					"full_name":'', // 姓名
					"time_of_appointment": "1970-01-01 00:00:00",
					"examine_state": "未审核",
					"examine_reply": "",
					"registration_reservation_id": 0,
				},

				// 表单字段
				form: {
					"reservation_number":this.$get_stamp(), // 预约编号
					"doctor_number": 0, // 医生编号
					"name_of_doctor":'', // 医生姓名
					"department_name":'', // 科室名称
					"patient": 0, // 患者
					"full_name":'', // 姓名
					"time_of_appointment": "1970-01-01 00:00:00",
					"examine_state": "未审核",
					"examine_reply": "",
					"registration_reservation_id": 0,

				},
				disabledObj:{
					"reservation_number_isDisabled": false,
					"doctor_number_isDisabled": false,
					"name_of_doctor_isDisabled": false,
					"department_name_isDisabled": false,
					"patient_isDisabled": false,
					"full_name_isDisabled": false,
					"time_of_appointment_isDisabled": false,
				},
				// 用户列表
				list_user_doctor_number: [],
				// 用户列表
				list_user_patient: [],

				// ID字段
				field: "registration_reservation_id",
			}
		},
		methods: {
			/**
			 * 获取医生用户列表
			 */
			async get_list_user_doctor_number() {
				// if(this.user_group !== "管理员" && this.form["doctor_number"] === 0) {
				//     this.form["doctor_number"] = this.user.user_id;
				// }
				var json = await this.$get("~/api/user/get_list?user_group=医生");
				if(json.result && json.result.list){
					this.list_user_doctor_number = json.result.list;
				}
				else if(json.error){
					console.error(json.error);
				}
			},
			/**
			 * 获取患者用户列表
			 */
			async get_list_user_patient() {
				// if(this.user_group !== "管理员" && this.form["patient"] === 0) {
				//     this.form["patient"] = this.user.user_id;
				// }
				var json = await this.$get("~/api/user/get_list?user_group=患者");
				if(json.result && json.result.list){
					this.list_user_patient = json.result.list;
				}
				else if(json.error){
					console.error(json.error);
				}
			},
			async get_user_session_patient(){
				var _this = this;
				var json = await this.$get("~/api/user_group/get_obj?name=患者");
				if(json.result && json.result.obj){
					var source_table = json.result.obj.source_table;
					var user_id = _this.$store.state.user.user_id;
					if (user_id){
						var url = "~/api/"+source_table+"/get_obj?"
						this.$get(url, {"user_id":_this.$store.state.user.user_id}, function(res) {
							if (res.result && res.result.obj) {
								var arr = []
								for (let key in res.result.obj) {
									arr.push(key)
								}
								var arrForm = []
								for (let key in _this.form) {
									arrForm.push(key)
								}
								_this.form["patient"] = user_id
								_this.disabledObj['patient' + '_isDisabled'] = true
								for (var i=0;i<arr.length;i++){
									for (var j=0;j<arrForm.length;j++){
										if (arr[i]===arrForm[j]){
											if (arr[i]!=="patient") {
												_this.form[arrForm[j]] = res.result.obj[arr[i]]
												_this.disabledObj[arrForm[j] + '_isDisabled'] = true
												break;
											}
										}
									}
								}
							}
						});
					}
				}
				else if(json.error){
					console.error(json.error);
				}
			},

			/**
			 * 修改文件
			 * @param { Object } files 上传文件对象
			 * @param { String } str 表单的属性名
			 */
			change_file(files, str) {
				var form = new FormData();
				form.append("file", files[0]);
				this.$post("~/api/registration_reservation/upload?", form, (res) => {
					if (res.result) {
						this.form[str] = res.result.url;
					} else if (res.error) {
						this.$toast(res.error.message);
					}
				});
			},

			/**
			 * 获取对象后获取缓存表单
			 * @param {Object} json
			 * @param {Object} func
			 */
			get_obj_before(param){
				var form = $.db.get("form");
				if (form) {
					this.obj = $.push(this.obj ,form);
					this.form = $.push(this.form ,form);
				}
				var arr = []
				for (let key in form) {
					arr.push(key)
				}
				for (var i=0;i<arr.length;i++){
					this.disabledObj[arr[i] + '_isDisabled'] = true
				}
				this.form["time_of_appointment"] = this.$toTime(parseInt(this.form["time_of_appointment"]),"yyyy-MM-dd")
				return param;
			},

			/**
			 * 获取对象后获取缓存表单
			 * @param {Object} json
			 * @param {Object} func
			 */
			get_obj_after(json ,func){
				var form = $.db.get("form");
				var obj = Object.assign({} ,form ,this.obj);
				if (form) {
					this.obj = $.push(this.obj ,obj);
				}
				if (form) {
					this.form = $.push(this.form ,form);
				}
				if(func){
					func(json);
				}
			},

		},
		created() {
			this.get_list_user_doctor_number();
			this.get_user_session_patient();
			this.get_list_user_patient();
		}
	}
</script>

<style>
</style>
