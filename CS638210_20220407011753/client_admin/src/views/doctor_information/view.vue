<template>
	<el-main class="bg">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">
			<el-col v-if="user_group === '管理员' || $check_field('get','doctor_number') || $check_field('add','doctor_number') || $check_field('set','doctor_number')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="医生编号" prop="doctor_number">
						<div v-if="user_group !== '管理员'">
							{{ get_user_session_doctor_number(form['doctor_number']) }}
							<!--<el-input id="business_name" v-model="form['doctor_number']" placeholder="请输入医生编号"-->
							<!--v-if="user_group === '管理员' || (form['doctor_information_id'] && $check_field('set','doctor_number')) || (!form['doctor_information_id'] && $check_field('add','doctor_number'))" :disabled="disabledObj['doctor_number_isDisabled']"></el-input>-->
							<!--<div v-else-if="$check_field('get','doctor_number')">{{form['doctor_number']}}</div>-->
							<el-select v-if="user_group === '管理员' || (form['doctor_information_id'] && $check_field('set','doctor_number')) || (!form['doctor_information_id'] && $check_field('add','doctor_number'))" id="doctor_number" v-model="form['doctor_number']" :disabled="disabledObj['doctor_number_isDisabled']">
								<el-option v-for="o in list_user_doctor_number" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
										   :value="o['user_id']">
								</el-option>
							</el-select>
							<el-select v-else-if="$check_field('get','doctor_number')" id="doctor_number" v-model="form['doctor_number']" :disabled="true">
								<el-option v-for="o in list_user_doctor_number" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
										   :value="o['user_id']">
								</el-option>
							</el-select>
						</div>
						<el-select v-else id="doctor_number" v-model="form['doctor_number']" :disabled="disabledObj['doctor_number_isDisabled']">
							<el-option v-for="o in list_user_doctor_number" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
									   :value="o['user_id']">
							</el-option>
						</el-select>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','name_of_doctor') || $check_field('add','name_of_doctor') || $check_field('set','name_of_doctor')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="医生姓名" prop="name_of_doctor">
					<el-input id="name_of_doctor" v-model="form['name_of_doctor']" placeholder="请输入医生姓名"
							  v-if="user_group === '管理员' || (form['doctor_information_id'] && $check_field('set','name_of_doctor')) || (!form['doctor_information_id'] && $check_field('add','name_of_doctor'))" :disabled="disabledObj['name_of_doctor_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','name_of_doctor')">{{form['name_of_doctor']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','occupation') || $check_field('add','occupation') || $check_field('set','occupation')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="职业" prop="occupation">
					<el-input id="occupation" v-model="form['occupation']" placeholder="请输入职业"
							  v-if="user_group === '管理员' || (form['doctor_information_id'] && $check_field('set','occupation')) || (!form['doctor_information_id'] && $check_field('add','occupation'))" :disabled="disabledObj['occupation_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','occupation')">{{form['occupation']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','department_name') || $check_field('add','department_name') || $check_field('set','department_name')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="科室名称" prop="department_name">
					<el-select id="department_name" v-model="form['department_name']"
						v-if="user_group === '管理员' || (form['doctor_information_id'] && $check_field('set','department_name')) || (!form['doctor_information_id'] && $check_field('add','department_name'))">
						<el-option v-for="o in list_department_name" :key="o['department_name']" :label="o['department_name']"
							:value="o['department_name']">
						</el-option>
					</el-select>
					<div v-else-if="$check_field('get','department_name')">{{form['department_name']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','areas_of_expertise') || $check_field('add','areas_of_expertise') || $check_field('set','areas_of_expertise')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="擅长领域" prop="areas_of_expertise">
					<el-input id="areas_of_expertise" v-model="form['areas_of_expertise']" placeholder="请输入擅长领域"
							  v-if="user_group === '管理员' || (form['doctor_information_id'] && $check_field('set','areas_of_expertise')) || (!form['doctor_information_id'] && $check_field('add','areas_of_expertise'))" :disabled="disabledObj['areas_of_expertise_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','areas_of_expertise')">{{form['areas_of_expertise']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','doctor_picture') || $check_field('add','doctor_picture') || $check_field('set','doctor_picture')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="医生图片" prop="doctor_picture">
					<el-upload :disabled="disabledObj['doctor_picture_isDisabled']" id="doctor_picture" class="avatar-uploader" drag
						accept="image/gif, image/jpeg, image/png, image/jpg" action="" :http-request="upload_doctor_picture"
						:show-file-list="false" v-if="user_group === '管理员' || (form['doctor_information_id'] && $check_field('set','doctor_picture')) || (!form['doctor_information_id'] && $check_field('add','doctor_picture'))">
						<img v-if="form['doctor_picture']" :src="$fullUrl(form['doctor_picture'])" class="avatar">
						<i v-else class="el-icon-plus avatar-uploader-icon"></i>
					</el-upload>
					<el-image v-else-if="$check_field('get','doctor_picture')" style="width: 100px; height: 100px"
						:src="$fullUrl(form['doctor_picture'])" :preview-src-list="[$fullUrl(form['doctor_picture'])]">
						<div slot="error" class="image-slot">
							<img src="../../../public/img/error.png" style="width: 90px; height: 90px" />
						</div>
					</el-image>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','professional_experience') || $check_field('add','professional_experience') || $check_field('set','professional_experience')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="职业经历" prop="professional_experience">
					<el-input type="textarea" id="professional_experience" v-model="form['professional_experience']" placeholder="请输入职业经历"
						v-if="user_group === '管理员' || (form['doctor_information_id'] && $check_field('set','professional_experience')) || (!form['doctor_information_id'] && $check_field('add','professional_experience'))" :disabled="disabledObj['professional_experience_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','professional_experience')">{{form['professional_experience']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','honorary_awards') || $check_field('add','honorary_awards') || $check_field('set','honorary_awards')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="荣誉嘉奖" prop="honorary_awards">
					<el-input type="textarea" id="honorary_awards" v-model="form['honorary_awards']" placeholder="请输入荣誉嘉奖"
						v-if="user_group === '管理员' || (form['doctor_information_id'] && $check_field('set','honorary_awards')) || (!form['doctor_information_id'] && $check_field('add','honorary_awards'))" :disabled="disabledObj['honorary_awards_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','honorary_awards')">{{form['honorary_awards']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','academic_achievements') || $check_field('add','academic_achievements') || $check_field('set','academic_achievements')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="学术成就" prop="academic_achievements">
					<el-input type="textarea" id="academic_achievements" v-model="form['academic_achievements']" placeholder="请输入学术成就"
						v-if="user_group === '管理员' || (form['doctor_information_id'] && $check_field('set','academic_achievements')) || (!form['doctor_information_id'] && $check_field('add','academic_achievements'))" :disabled="disabledObj['academic_achievements_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','academic_achievements')">{{form['academic_achievements']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','doctor_style') || $check_field('add','doctor_style') || $check_field('set','doctor_style')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="医生风采" prop="doctor_style">
					<el-input type="textarea" id="doctor_style" v-model="form['doctor_style']" placeholder="请输入医生风采"
						v-if="user_group === '管理员' || (form['doctor_information_id'] && $check_field('set','doctor_style')) || (!form['doctor_information_id'] && $check_field('add','doctor_style'))" :disabled="disabledObj['doctor_style_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','doctor_style')">{{form['doctor_style']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','doctor_profile') || $check_field('add','doctor_profile') || $check_field('set','doctor_profile')" :xs="24" :sm="24" :lg="24">
				<el-form-item label="医生简介" prop="doctor_profile">
					<quill-editor v-model.number="form['doctor_profile']"
						v-if="user_group === '管理员' || (form['doctor_information_id'] && $check_field('set','doctor_profile')) || (!form['doctor_information_id'] && $check_field('add','doctor_profile')) ">
					</quill-editor>
					<div v-else-if="$check_field('get','doctor_profile')" v-html="form['doctor_profile']"></div>
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
				field: "doctor_information_id",
				url_add: "~/api/doctor_information/add?",
				url_set: "~/api/doctor_information/set?",
				url_get_obj: "~/api/doctor_information/get_obj?",
				url_upload: "~/api/doctor_information/upload?",

				query: {
					"doctor_information_id": 0,
				},

				form: {
					"doctor_number": 0, // 医生编号
					"name_of_doctor":'', // 医生姓名
					"occupation":'', // 职业
					"department_name":'', // 科室名称
					"areas_of_expertise":'', // 擅长领域
					"doctor_picture":'', // 医生图片
					"professional_experience":'', // 职业经历
					"honorary_awards":'', // 荣誉嘉奖
					"academic_achievements":'', // 学术成就
					"doctor_style":'', // 医生风采
					"doctor_profile":'', // 医生简介
					"doctor_information_id": 0, // ID

				},
				disabledObj:{
					"doctor_number_isDisabled": false,
					"name_of_doctor_isDisabled": false,
					"occupation_isDisabled": false,
					"department_name_isDisabled": false,
					"areas_of_expertise_isDisabled": false,
					"doctor_picture_isDisabled": false,
					"professional_experience_isDisabled": false,
					"honorary_awards_isDisabled": false,
					"academic_achievements_isDisabled": false,
					"doctor_style_isDisabled": false,
					"doctor_profile_isDisabled": false,
				},
				// 用户列表
				list_user_doctor_number: [],
				// 用户组
				group_user_doctor_number: "",
				//科室名称选项列表
				list_department_name: [],

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
			 * 获取医生用户组
			 */
			async get_group_user_doctor_number() {
				this.form["doctor_number"] = this.user.user_id;
				var json = await this.$get("~/api/user_group/get_obj?name=医生");
				if(json.result && json.result.obj){
					this.group_user_doctor_number = json.result.obj;
				}
				else if(json.error){
					console.error(json.error);
				}
			},
			get_user_session_doctor_number(id){
				var _this = this;
				var user_id = {"user_id":id}
				var url = "~/api/"+_this.group_user_doctor_number.source_table+"/get_obj?"
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
									if (arr[i]!=="doctor_number") {
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
			 * 获取科室名称列表
			 */
			async get_list_department_name() {
				var json = await this.$get("~/api/department_management/get_list?");
				if(json.result && json.result.list){
					this.list_department_name = json.result.list;
				}
				else if(json.error){
					console.error(json.error);
				}
			},
			/**
			 * 上传医生图片
			 * @param {Object} param图片参数
			 */
			upload_doctor_picture(param){
				this.uploadFile(param.file, "doctor_picture");
			},

			/**
			 * 获取对象之前
			 * @param {Object} param
			 */
			get_obj_before(param) {
				var form = "";
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
				$.db.del("form");
				return param;
			},

			/**
			 * 获取对象之后
			 * @param {Object} json
			 * @param {Object} func
			 */
			get_obj_after(json, func){

			},

			is_view(){
				var bl = this.user_group == "管理员";

				if(!bl){
					bl = this.$check_action('/doctor_information/table','add');
					console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
				}
				if(!bl){
					bl = this.$check_action('/doctor_information/table','set');
					console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
				}
				if(!bl){
					bl = this.$check_action('/doctor_information/view','add');
					console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
				}
				if(!bl){
					bl = this.$check_action('/doctor_information/view','set');
					console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
				}
				if(!bl){
					bl = this.$check_action('/doctor_information/view','get');
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
			this.get_group_user_doctor_number();
			this.get_list_department_name();
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
