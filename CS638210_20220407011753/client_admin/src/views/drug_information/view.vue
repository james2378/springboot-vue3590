<template>
	<el-main class="bg">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">
			<el-col v-if="user_group === '管理员' || $check_field('get','drug_number') || $check_field('add','drug_number') || $check_field('set','drug_number')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="药品编号" prop="drug_number">
					<el-input id="drug_number" v-model="form['drug_number']" placeholder="请输入药品编号"
							  v-if="user_group === '管理员' || (form['drug_information_id'] && $check_field('set','drug_number')) || (!form['drug_information_id'] && $check_field('add','drug_number'))" :disabled="true"></el-input>
					<div v-else-if="$check_field('get','drug_number')">{{form['drug_number']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','drug_name') || $check_field('add','drug_name') || $check_field('set','drug_name')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="药品名称" prop="drug_name">
					<el-input id="drug_name" v-model="form['drug_name']" placeholder="请输入药品名称"
							  v-if="user_group === '管理员' || (form['drug_information_id'] && $check_field('set','drug_name')) || (!form['drug_information_id'] && $check_field('add','drug_name'))" :disabled="disabledObj['drug_name_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','drug_name')">{{form['drug_name']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','drug_cover') || $check_field('add','drug_cover') || $check_field('set','drug_cover')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="药品封面" prop="drug_cover">
					<el-upload :disabled="disabledObj['drug_cover_isDisabled']" id="drug_cover" class="avatar-uploader" drag
						accept="image/gif, image/jpeg, image/png, image/jpg" action="" :http-request="upload_drug_cover"
						:show-file-list="false" v-if="user_group === '管理员' || (form['drug_information_id'] && $check_field('set','drug_cover')) || (!form['drug_information_id'] && $check_field('add','drug_cover'))">
						<img v-if="form['drug_cover']" :src="$fullUrl(form['drug_cover'])" class="avatar">
						<i v-else class="el-icon-plus avatar-uploader-icon"></i>
					</el-upload>
					<el-image v-else-if="$check_field('get','drug_cover')" style="width: 100px; height: 100px"
						:src="$fullUrl(form['drug_cover'])" :preview-src-list="[$fullUrl(form['drug_cover'])]">
						<div slot="error" class="image-slot">
							<img src="../../../public/img/error.png" style="width: 90px; height: 90px" />
						</div>
					</el-image>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','drug_type') || $check_field('add','drug_type') || $check_field('set','drug_type')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="药品类型" prop="drug_type">
					<el-select id="drug_type" v-model="form['drug_type']"
						v-if="user_group === '管理员' || (form['drug_information_id'] && $check_field('set','drug_type')) || (!form['drug_information_id'] && $check_field('add','drug_type'))">
						<el-option v-for="o in list_drug_type" :key="o['drug_type']" :label="o['drug_type']"
							:value="o['drug_type']">
						</el-option>
					</el-select>
					<div v-else-if="$check_field('get','drug_type')">{{form['drug_type']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','unit_price') || $check_field('add','unit_price') || $check_field('set','unit_price')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="单价" prop="unit_price">
					<el-input-number id="unit_price" v-model.number="form['unit_price']"
						v-if="user_group === '管理员' || (form['drug_information_id'] && $check_field('set','unit_price')) || (!form['drug_information_id'] && $check_field('add','unit_price'))"></el-input-number>
					<div v-else-if="$check_field('get','unit_price')">{{form['unit_price']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','main_function') || $check_field('add','main_function') || $check_field('set','main_function')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="主治功能" prop="main_function">
					<el-input type="textarea" id="main_function" v-model="form['main_function']" placeholder="请输入主治功能"
						v-if="user_group === '管理员' || (form['drug_information_id'] && $check_field('set','main_function')) || (!form['drug_information_id'] && $check_field('add','main_function'))" :disabled="disabledObj['main_function_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','main_function')">{{form['main_function']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','drug_introduction') || $check_field('add','drug_introduction') || $check_field('set','drug_introduction')" :xs="24" :sm="24" :lg="24">
				<el-form-item label="药品介绍" prop="drug_introduction">
					<quill-editor v-model.number="form['drug_introduction']"
						v-if="user_group === '管理员' || (form['drug_information_id'] && $check_field('set','drug_introduction')) || (!form['drug_information_id'] && $check_field('add','drug_introduction')) ">
					</quill-editor>
					<div v-else-if="$check_field('get','drug_introduction')" v-html="form['drug_introduction']"></div>
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
				field: "drug_information_id",
				url_add: "~/api/drug_information/add?",
				url_set: "~/api/drug_information/set?",
				url_get_obj: "~/api/drug_information/get_obj?",
				url_upload: "~/api/drug_information/upload?",

				query: {
					"drug_information_id": 0,
				},

				form: {
					"drug_number":this.$get_stamp(), // 药品编号
					"drug_name":'', // 药品名称
					"drug_cover":'', // 药品封面
					"drug_type":'', // 药品类型
					"unit_price":0, // 单价
					"main_function":'', // 主治功能
					"drug_introduction":'', // 药品介绍
					"drug_information_id": 0, // ID

				},
				disabledObj:{
					"drug_number_isDisabled": false,
					"drug_name_isDisabled": false,
					"drug_cover_isDisabled": false,
					"drug_type_isDisabled": false,
					"main_function_isDisabled": false,
					"drug_introduction_isDisabled": false,
				},
				//药品类型选项列表
				list_drug_type: [],

			}
		},
		methods: {
			/**
			 * 上传药品封面
			 * @param {Object} param图片参数
			 */
			upload_drug_cover(param){
				this.uploadFile(param.file, "drug_cover");
			},
			/**
			 * 获取药品类型列表
			 */
			async get_list_drug_type() {
				var json = await this.$get("~/api/drug_category/get_list?");
				if(json.result && json.result.list){
					this.list_drug_type = json.result.list;
				}
				else if(json.error){
					console.error(json.error);
				}
			},

			/**
			 * 获取对象之前
			 * @param {Object} param
			 */
			get_obj_before(param) {
				var form = "";
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
					bl = this.$check_action('/drug_information/table','add');
					console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
				}
				if(!bl){
					bl = this.$check_action('/drug_information/table','set');
					console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
				}
				if(!bl){
					bl = this.$check_action('/drug_information/view','add');
					console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
				}
				if(!bl){
					bl = this.$check_action('/drug_information/view','set');
					console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
				}
				if(!bl){
					bl = this.$check_action('/drug_information/view','get');
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
			this.get_list_drug_type();
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
