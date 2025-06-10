<template>
	<el-main class="bg">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">
			<el-col v-if="user_group === '管理员' || $check_field('get','patient') || $check_field('add','patient') || $check_field('set','patient')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="患者" prop="patient">
						<el-select v-if="user_group === '管理员' || (form['check_information_id'] && $check_field('set','patient')) || (!form['check_information_id'] && $check_field('add','patient'))" id="patient" v-model="form['patient']" :disabled="disabledObj['patient_isDisabled']">
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
							  v-if="user_group === '管理员' || (form['check_information_id'] && $check_field('set','full_name')) || (!form['check_information_id'] && $check_field('add','full_name'))" :disabled="disabledObj['full_name_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','full_name')">{{form['full_name']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','inspection_time') || $check_field('add','inspection_time') || $check_field('set','inspection_time')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="检查时间" prop="inspection_time">
					<el-date-picker :disabled="disabledObj['inspection_time_isDisabled']" v-if="user_group === '管理员' || (form['check_information_id'] && $check_field('set','inspection_time')) || (!form['check_information_id'] && $check_field('add','inspection_time'))" id="inspection_time"
						v-model="form['inspection_time']" type="date" placeholder="选择日期">
					</el-date-picker>
					<div v-else-if="$check_field('get','inspection_time')">{{form['inspection_time']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','inspection_items') || $check_field('add','inspection_items') || $check_field('set','inspection_items')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="检查项目" prop="inspection_items">
					<el-input id="inspection_items" v-model="form['inspection_items']" placeholder="请输入检查项目"
							  v-if="user_group === '管理员' || (form['check_information_id'] && $check_field('set','inspection_items')) || (!form['check_information_id'] && $check_field('add','inspection_items'))" :disabled="disabledObj['inspection_items_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','inspection_items')">{{form['inspection_items']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','check_amount') || $check_field('add','check_amount') || $check_field('set','check_amount')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="检查金额" prop="check_amount">
					<el-input-number id="check_amount" v-model.number="form['check_amount']"
						v-if="user_group === '管理员' || (form['check_information_id'] && $check_field('set','check_amount')) || (!form['check_information_id'] && $check_field('add','check_amount'))"></el-input-number>
					<div v-else-if="$check_field('get','check_amount')">{{form['check_amount']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','doctor_number') || $check_field('add','doctor_number') || $check_field('set','doctor_number')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="医生编号" prop="doctor_number">
						<el-select v-if="user_group === '管理员' || (form['check_information_id'] && $check_field('set','doctor_number')) || (!form['check_information_id'] && $check_field('add','doctor_number'))" id="doctor_number" v-model="form['doctor_number']" :disabled="disabledObj['doctor_number_isDisabled']">
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
				field: "check_information_id",
				url_add: "~/api/check_information/add?",
				url_set: "~/api/check_information/set?",
				url_get_obj: "~/api/check_information/get_obj?",
				url_upload: "~/api/check_information/upload?",

				query: {
					"check_information_id": 0,
				},

				form: {
					"patient": 0, // 患者
					"full_name":'', // 姓名
					"inspection_time":'', // 检查时间
					"inspection_items":'', // 检查项目
					"check_amount":0, // 检查金额
					"doctor_number": 0, // 医生编号
					"check_information_id": 0, // ID

				},
				disabledObj:{
					"patient_isDisabled": false,
					"full_name_isDisabled": false,
					"inspection_time_isDisabled": false,
					"inspection_items_isDisabled": false,
					"doctor_number_isDisabled": false,
				},
				// 用户列表
				list_user_patient: [],
				// 用户列表
				list_user_doctor_number: [],

			}
		},
		methods: {
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
						case "患者":
							if(param["patient"] > 0){
								param["patient"] = this.user.user_id;
							}
							break;
						case "医生编号":
							if(param["doctor_number"] > 0){
								param["doctor_number"] = this.user.user_id;
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
        if (this.form["inspection_time"].indexOf("-")===-1){
          this.form["inspection_time"] = this.$toTime(parseInt(this.form["inspection_time"]),"yyyy-MM-dd")
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
				if(parseInt(this.form["inspection_time"]) > 9999){
					this.form["inspection_time"] = this.$toTime(parseInt(this.form["inspection_time"]),"yyyy-MM-dd")
				}

			},

			is_view(){
				var bl = this.user_group == "管理员";

				if(!bl){
					bl = this.$check_action('/check_information/table','add');
					console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
				}
				if(!bl){
					bl = this.$check_action('/check_information/table','set');
					console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
				}
				if(!bl){
					bl = this.$check_action('/check_information/view','add');
					console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
				}
				if(!bl){
					bl = this.$check_action('/check_information/view','set');
					console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
				}
				if(!bl){
					bl = this.$check_action('/check_information/view','get');
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
			this.get_list_user_patient();
			this.get_list_user_doctor_number();
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
