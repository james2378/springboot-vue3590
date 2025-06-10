<template>
	<el-main class="bg">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">
			<el-col v-if="user_group === '管理员' || $check_field('get','reservation_number') || $check_field('add','reservation_number') || $check_field('set','reservation_number')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="预约编号" prop="reservation_number">
					<el-input id="reservation_number" v-model="form['reservation_number']" placeholder="请输入预约编号"
							  v-if="user_group === '管理员' || (form['registration_reservation_id'] && $check_field('set','reservation_number')) || (!form['registration_reservation_id'] && $check_field('add','reservation_number'))" :disabled="true"></el-input>
					<div v-else-if="$check_field('get','reservation_number')">{{form['reservation_number']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','doctor_number') || $check_field('add','doctor_number') || $check_field('set','doctor_number')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="医生编号" prop="doctor_number">
						<el-select v-if="user_group === '管理员' || (form['registration_reservation_id'] && $check_field('set','doctor_number')) || (!form['registration_reservation_id'] && $check_field('add','doctor_number'))" id="doctor_number" v-model="form['doctor_number']" :disabled="disabledObj['doctor_number_isDisabled']">
							<el-option v-for="o in list_user_doctor_number" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
									   :value="o['user_id']">
							</el-option>
						</el-select>
						<el-select v-else-if="$check_field('get','doctor_number')" id="doctor_number" v-model="form['doctor_number']" :disabled="true">
							<el-option v-for="o in list_user_doctor_number" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
									   :value="o['user_id']">
							</el-option>
						</el-select>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','name_of_doctor') || $check_field('add','name_of_doctor') || $check_field('set','name_of_doctor')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="医生姓名" prop="name_of_doctor">
					<el-input id="name_of_doctor" v-model="form['name_of_doctor']" placeholder="请输入医生姓名"
							  v-if="user_group === '管理员' || (form['registration_reservation_id'] && $check_field('set','name_of_doctor')) || (!form['registration_reservation_id'] && $check_field('add','name_of_doctor'))" :disabled="disabledObj['name_of_doctor_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','name_of_doctor')">{{form['name_of_doctor']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','department_name') || $check_field('add','department_name') || $check_field('set','department_name')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="科室名称" prop="department_name">
					<el-input id="department_name" v-model="form['department_name']" placeholder="请输入科室名称"
							  v-if="user_group === '管理员' || (form['registration_reservation_id'] && $check_field('set','department_name')) || (!form['registration_reservation_id'] && $check_field('add','department_name'))" :disabled="disabledObj['department_name_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','department_name')">{{form['department_name']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','patient') || $check_field('add','patient') || $check_field('set','patient')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="患者" prop="patient">
						<div v-if="user_group !== '管理员'">
							{{ get_user_session_patient(form['patient']) }}
							<!--<el-input id="business_name" v-model="form['patient']" placeholder="请输入患者"-->
							<!--v-if="user_group === '管理员' || (form['registration_reservation_id'] && $check_field('set','patient')) || (!form['registration_reservation_id'] && $check_field('add','patient'))" :disabled="disabledObj['patient_isDisabled']"></el-input>-->
							<!--<div v-else-if="$check_field('get','patient')">{{form['patient']}}</div>-->
							<el-select v-if="user_group === '管理员' || (form['registration_reservation_id'] && $check_field('set','patient')) || (!form['registration_reservation_id'] && $check_field('add','patient'))" id="patient" v-model="form['patient']" :disabled="disabledObj['patient_isDisabled']">
								<el-option v-for="o in list_user_patient" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
										   :value="o['user_id']">
								</el-option>
							</el-select>
							<el-select v-else-if="$check_field('get','patient')" id="patient" v-model="form['patient']" :disabled="true">
								<el-option v-for="o in list_user_patient" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
										   :value="o['user_id']">
								</el-option>
							</el-select>
						</div>
						<el-select v-else id="patient" v-model="form['patient']" :disabled="disabledObj['patient_isDisabled']">
							<el-option v-for="o in list_user_patient" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
									   :value="o['user_id']">
							</el-option>
						</el-select>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','full_name') || $check_field('add','full_name') || $check_field('set','full_name')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="姓名" prop="full_name">
					<el-input id="full_name" v-model="form['full_name']" placeholder="请输入姓名"
							  v-if="user_group === '管理员' || (form['registration_reservation_id'] && $check_field('set','full_name')) || (!form['registration_reservation_id'] && $check_field('add','full_name'))" :disabled="disabledObj['full_name_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','full_name')">{{form['full_name']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','time_of_appointment') || $check_field('add','time_of_appointment') || $check_field('set','time_of_appointment')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="预约时间" prop="time_of_appointment">
					<el-date-picker :disabled="disabledObj['time_of_appointment_isDisabled']" v-if="user_group === '管理员' || (form['registration_reservation_id'] && $check_field('set','time_of_appointment')) || (!form['registration_reservation_id'] && $check_field('add','time_of_appointment'))" id="time_of_appointment"
						v-model="form['time_of_appointment']" type="date" placeholder="选择日期">
					</el-date-picker>
					<div v-else-if="$check_field('get','time_of_appointment')">{{form['time_of_appointment']}}</div>
				</el-form-item>
			</el-col>
			<el-col :xs="24" :sm="12" :lg="8">
				<el-form-item label="审核状态" prop="examine_state">
					<el-select id="examine_state" v-model="form['examine_state']"
						v-if="user_group === '管理员' || (form['examine_state'] && $check_examine()) || (!form['examine_state'] && $check_examine())">
						<el-option key="未审核" label="未审核" value="未审核"></el-option>
						<el-option key="已通过" label="已通过" value="已通过"></el-option>
						<el-option key="未通过" label="未通过" value="未通过"></el-option>
					</el-select>
					<div v-else-if="$check_field('get','examine_state')">{{form["examine_state"]}}</div>
				</el-form-item>
			</el-col>
			<el-col :xs="24" :sm="12" :lg="8">
				<el-form-item label="审核回复" prop="examine_reply">
					<el-input id="examine_reply" v-model="form['examine_reply']" placeholder="请输入审核回复"
						v-if="user_group === '管理员' || (form['examine_reply'] && $check_examine()) || (!form['examine_reply'] && $check_examine())"></el-input>
					<div v-else-if="$check_field('get','examine_reply')">{{form["examine_reply"]}}</div>
				</el-form-item>
			</el-col>
			<el-col :xs="24" :sm="12" :lg="8">
				<el-form-item>
					<el-button type="primary" @click="submit()">提交</el-button>
					<el-button @click="cancel()">取消</el-button>
				</el-form-item>
			</el-col>

		</el-form>
	</el-main>
</template>

<script>
	import mixin from "@/mixins/page.js";

	export default {
		mixins: [mixin],
		data() {
			return {
				field: "registration_reservation_id",
				url_add: "~/api/registration_reservation/add?",
				url_set: "~/api/registration_reservation/set?",
				url_get_obj: "~/api/registration_reservation/get_obj?",
				url_upload: "~/api/registration_reservation/upload?",

				query: {
					"registration_reservation_id": 0,
				},

				form: {
					"reservation_number":this.$get_stamp(), // 预约编号
					"doctor_number": 0, // 医生编号
					"name_of_doctor":'', // 医生姓名
					"department_name":'', // 科室名称
					"patient": 0, // 患者
					"full_name":'', // 姓名
					"time_of_appointment":'', // 预约时间
					"examine_state": "未审核",
					"examine_reply": "",
					"registration_reservation_id": 0, // ID

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
				// 用户组
				group_user_patient: "",

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
			get_user_doctor_number(id){
				var obj = this.list_user_doctor_number.getObj({"user_id":id});
				var ret = "";
				if(obj){
					if(obj.nickname){
						ret = obj.nickname;}
					else{
						ret = obj.username;
					}
				}
				return ret;
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
			/**
			 * 获取患者用户组
			 */
			async get_group_user_patient() {
				this.form["patient"] = this.user.user_id;
				var json = await this.$get("~/api/user_group/get_obj?name=患者");
				if(json.result && json.result.obj){
					this.group_user_patient = json.result.obj;
				}
				else if(json.error){
					console.error(json.error);
				}
			},
			get_user_session_patient(id){
				var _this = this;
				var user_id = {"user_id":id}
				var url = "~/api/"+_this.group_user_patient.source_table+"/get_obj?"
				this.$get(url, user_id, function(res) {
					if (res.result && res.result.obj) {
						var arr = []
						for (let key in res.result.obj) {
							arr.push(key)
						}
						var arrForm = []
						for (let key in _this.form) {
							arrForm.push(key)
						}
						for (var i=0;i<arr.length;i++){
							for (var j=0;j<arrForm.length;j++){
								if (arr[i]===arrForm[j]){
									if (arr[i]!=="patient") {
										_this.form[arrForm[j]] = res.result.obj[arr[i]]
										_this.disabledObj[arrForm[j] + '_isDisabled'] = true
										break;
									}else {
										_this.disabledObj[arrForm[j] + '_isDisabled'] = true
									}
								}
							}
						}
					}
				});
			},
			get_user_patient(id){
				var obj = this.list_user_patient.getObj({"user_id":id});
				var ret = "";
				if(obj){
					if(obj.nickname){
						ret = obj.nickname;}
					else{
						ret = obj.username;
					}
				}
				return ret;
			},

			/**
			 * 获取对象之前
			 * @param {Object} param
			 */
			get_obj_before(param) {
				var form = "";
				// 获取缓存数据附加
				form = $.db.get("form");
				$.push(this.form ,form);
				/**
				* 请求列表前
				* @param {Object} param
				*/
				var user_group = this.user.user_group;
				if (user_group !== "管理员") {
					switch (user_group) {
						case "医生编号":
							if(param["doctor_number"] > 0){
								param["doctor_number"] = this.user.user_id;
							}
							break;
						case "患者":
							if(param["patient"] > 0){
								param["patient"] = this.user.user_id;
							}
							break;
					}
				}
				if(this.form && form){
					Object.keys(this.form).forEach(key => {
						Object.keys(form).forEach(dbKey => {
							// if(dbKey === "charging_standard"){
							// 	this.form['charging_rules'] = form[dbKey];
							// 	this.disabledObj['charging_rules_isDisabled'] = true;
							// };
							if(key === dbKey){
								this.disabledObj[key+'_isDisabled'] = true;
							}
						})
					})
				}
        if (this.form["time_of_appointment"].indexOf("-")===-1){
          this.form["time_of_appointment"] = this.$toTime(parseInt(this.form["time_of_appointment"]),"yyyy-MM-dd")
        }
				$.db.del("form");
				return param;
			},

			/**
			 * 获取对象之后
			 * @param {Object} json
			 * @param {Object} func
			 */
			get_obj_after(json, func){
				if(parseInt(this.form["time_of_appointment"]) > 9999){
					this.form["time_of_appointment"] = this.$toTime(parseInt(this.form["time_of_appointment"]),"yyyy-MM-dd")
				}

			},

			is_view(){
				var bl = this.user_group == "管理员";

				if(!bl){
					bl = this.$check_action('/registration_reservation/table','add');
					console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
				}
				if(!bl){
					bl = this.$check_action('/registration_reservation/table','set');
					console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
				}
				if(!bl){
					bl = this.$check_action('/registration_reservation/view','add');
					console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
				}
				if(!bl){
					bl = this.$check_action('/registration_reservation/view','set');
					console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
				}
				if(!bl){
					bl = this.$check_action('/registration_reservation/view','get');
					console.log(bl ? "你有视图查询权限视作有查询权限" : "你没有视图查询权限");
				}

				console.log(bl ? "具有当前页面的查看权，请注意这不代表你有字段的查看权" : "无权查看当前页，请注意即便有字段查询权限没有页面查询权限也不行");

				return bl;
			},
			/**
			 * 上传文件
			 * @param {Object} param
			 */
			uploadimg(param) {
				this.uploadFile(param.file, "avatar");
			},

		},
		created() {
			this.get_list_user_doctor_number();
			this.get_list_user_patient();
			this.get_group_user_patient();
		}
	}
</script>

<style>
	.avatar-uploader .el-upload {
		border: 1px dashed #d9d9d9;
		border-radius: 6px;
		cursor: pointer;
		position: relative;
		overflow: hidden;
	}

	.avatar-uploader .el-upload:hover {
		border-color: #409EFF;
	}

	.avatar-uploader-icon {
		font-size: 28px;
		color: #8c939d;
		width: 178px;
		height: 178px;
		line-height: 178px;
		text-align: center;
	}

	.avatar {
		width: 178px;
		height: 178px;
		display: block;
	}
</style>
