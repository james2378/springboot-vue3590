<template>
	<el-main class="bg">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">
			<el-col v-if="user_group === '管理员' || $check_field('get','doctor_number') || $check_field('add','doctor_number') || $check_field('set','doctor_number')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="医生编号" prop="doctor_number">
						<el-select v-if="user_group === '管理员' || (form['visit_form_id'] && $check_field('set','doctor_number')) || (!form['visit_form_id'] && $check_field('add','doctor_number'))" id="doctor_number" v-model="form['doctor_number']" :disabled="disabledObj['doctor_number_isDisabled']">
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
							  v-if="user_group === '管理员' || (form['visit_form_id'] && $check_field('set','name_of_doctor')) || (!form['visit_form_id'] && $check_field('add','name_of_doctor'))" :disabled="disabledObj['name_of_doctor_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','name_of_doctor')">{{form['name_of_doctor']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','patient') || $check_field('add','patient') || $check_field('set','patient')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="患者" prop="patient">
						<el-select v-if="user_group === '管理员' || (form['visit_form_id'] && $check_field('set','patient')) || (!form['visit_form_id'] && $check_field('add','patient'))" id="patient" v-model="form['patient']" :disabled="disabledObj['patient_isDisabled']">
							<el-option v-for="o in list_user_patient" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
									   :value="o['user_id']">
							</el-option>
						</el-select>
						<el-select v-else-if="$check_field('get','patient')" id="patient" v-model="form['patient']" :disabled="true">
							<el-option v-for="o in list_user_patient" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
									   :value="o['user_id']">
							</el-option>
						</el-select>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','full_name') || $check_field('add','full_name') || $check_field('set','full_name')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="姓名" prop="full_name">
					<el-input id="full_name" v-model="form['full_name']" placeholder="请输入姓名"
							  v-if="user_group === '管理员' || (form['visit_form_id'] && $check_field('set','full_name')) || (!form['visit_form_id'] && $check_field('add','full_name'))" :disabled="disabledObj['full_name_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','full_name')">{{form['full_name']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','visit_time') || $check_field('add','visit_time') || $check_field('set','visit_time')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="就诊时间" prop="visit_time">
					<el-date-picker :disabled="disabledObj['visit_time_isDisabled']" v-if="user_group === '管理员' || (form['visit_form_id'] && $check_field('set','visit_time')) || (!form['visit_form_id'] && $check_field('add','visit_time'))" id="visit_time"
						v-model="form['visit_time']" type="date" placeholder="选择日期">
					</el-date-picker>
					<div v-else-if="$check_field('get','visit_time')">{{form['visit_time']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','pharmaceutical_products') || $check_field('add','pharmaceutical_products') || $check_field('set','pharmaceutical_products')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="药品金额品" prop="pharmaceutical_products">
					<el-input-number id="pharmaceutical_products" v-model.number="form['pharmaceutical_products']"
						v-if="user_group === '管理员' || (form['visit_form_id'] && $check_field('set','pharmaceutical_products')) || (!form['visit_form_id'] && $check_field('add','pharmaceutical_products'))"></el-input-number>
					<div v-else-if="$check_field('get','pharmaceutical_products')">{{form['pharmaceutical_products']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','diagnostic_results') || $check_field('add','diagnostic_results') || $check_field('set','diagnostic_results')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="诊断结果" prop="diagnostic_results">
					<el-input type="textarea" id="diagnostic_results" v-model="form['diagnostic_results']" placeholder="请输入诊断结果"
						v-if="user_group === '管理员' || (form['visit_form_id'] && $check_field('set','diagnostic_results')) || (!form['visit_form_id'] && $check_field('add','diagnostic_results'))" :disabled="disabledObj['diagnostic_results_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','diagnostic_results')">{{form['diagnostic_results']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','prescription') || $check_field('add','prescription') || $check_field('set','prescription')" :xs="24" :sm="24" :lg="24">
				<el-form-item label="处方" prop="prescription">
					<quill-editor v-model.number="form['prescription']"
						v-if="user_group === '管理员' || (form['visit_form_id'] && $check_field('set','prescription')) || (!form['visit_form_id'] && $check_field('add','prescription')) ">
					</quill-editor>
					<div v-else-if="$check_field('get','prescription')" v-html="form['prescription']"></div>
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
				field: "visit_form_id",
				url_add: "~/api/visit_form/add?",
				url_set: "~/api/visit_form/set?",
				url_get_obj: "~/api/visit_form/get_obj?",
				url_upload: "~/api/visit_form/upload?",

				query: {
					"visit_form_id": 0,
				},

				form: {
					"doctor_number": 0, // 医生编号
					"name_of_doctor":'', // 医生姓名
					"patient": 0, // 患者
					"full_name":'', // 姓名
					"visit_time":'', // 就诊时间
					"pharmaceutical_products":0, // 药品金额品
					"diagnostic_results":'', // 诊断结果
					"prescription":'', // 处方
					"visit_form_id": 0, // ID

				},
				disabledObj:{
					"doctor_number_isDisabled": false,
					"name_of_doctor_isDisabled": false,
					"patient_isDisabled": false,
					"full_name_isDisabled": false,
					"visit_time_isDisabled": false,
					"diagnostic_results_isDisabled": false,
					"prescription_isDisabled": false,
				},
				// 用户列表
				list_user_doctor_number: [],
				// 用户列表
				list_user_patient: [],

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
        if (this.form["visit_time"].indexOf("-")===-1){
          this.form["visit_time"] = this.$toTime(parseInt(this.form["visit_time"]),"yyyy-MM-dd")
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
				if(parseInt(this.form["visit_time"]) > 9999){
					this.form["visit_time"] = this.$toTime(parseInt(this.form["visit_time"]),"yyyy-MM-dd")
				}

			},

			is_view(){
				var bl = this.user_group == "管理员";

				if(!bl){
					bl = this.$check_action('/visit_form/table','add');
					console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
				}
				if(!bl){
					bl = this.$check_action('/visit_form/table','set');
					console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
				}
				if(!bl){
					bl = this.$check_action('/visit_form/view','add');
					console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
				}
				if(!bl){
					bl = this.$check_action('/visit_form/view','set');
					console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
				}
				if(!bl){
					bl = this.$check_action('/visit_form/view','get');
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
