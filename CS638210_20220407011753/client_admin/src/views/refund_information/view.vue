<template>
	<el-main class="bg">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">
			<el-col v-if="user_group === '管理员' || $check_field('get','patient') || $check_field('add','patient') || $check_field('set','patient')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="患者" prop="patient">
						<el-select v-if="user_group === '管理员' || (form['refund_information_id'] && $check_field('set','patient')) || (!form['refund_information_id'] && $check_field('add','patient'))" id="patient" v-model="form['patient']" :disabled="disabledObj['patient_isDisabled']">
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
							  v-if="user_group === '管理员' || (form['refund_information_id'] && $check_field('set','full_name')) || (!form['refund_information_id'] && $check_field('add','full_name'))" :disabled="disabledObj['full_name_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','full_name')">{{form['full_name']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','discharge_date_') || $check_field('add','discharge_date_') || $check_field('set','discharge_date_')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="出院日期" prop="discharge_date_">
					<el-date-picker :disabled="disabledObj['discharge_date__isDisabled']" v-if="user_group === '管理员' || (form['refund_information_id'] && $check_field('set','discharge_date_')) || (!form['refund_information_id'] && $check_field('add','discharge_date_'))" id="discharge_date_"
						v-model="form['discharge_date_']" type="date" placeholder="选择日期">
					</el-date-picker>
					<div v-else-if="$check_field('get','discharge_date_')">{{form['discharge_date_']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','prepayment_amount') || $check_field('add','prepayment_amount') || $check_field('set','prepayment_amount')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="预缴金额" prop="prepayment_amount">
					<el-input id="prepayment_amount" v-model="form['prepayment_amount']" placeholder="请输入预缴金额"
							  v-if="user_group === '管理员' || (form['refund_information_id'] && $check_field('set','prepayment_amount')) || (!form['refund_information_id'] && $check_field('add','prepayment_amount'))" :disabled="disabledObj['prepayment_amount_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','prepayment_amount')">{{form['prepayment_amount']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','consumption_amount') || $check_field('add','consumption_amount') || $check_field('set','consumption_amount')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="消费金额" prop="consumption_amount">
					<el-input-number id="consumption_amount" v-model.number="form['consumption_amount']"
						v-if="user_group === '管理员' || (form['refund_information_id'] && $check_field('set','consumption_amount')) || (!form['refund_information_id'] && $check_field('add','consumption_amount'))"></el-input-number>
					<div v-else-if="$check_field('get','consumption_amount')">{{form['consumption_amount']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','refund_amount_') || $check_field('add','refund_amount_') || $check_field('set','refund_amount_')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="退款金额" prop="refund_amount_">
					<el-input id="refund_amount_" v-model="form['refund_amount_']" placeholder="请输入退款金额"
							  v-if="user_group === '管理员' || (form['refund_information_id'] && $check_field('set','refund_amount_')) || (!form['refund_information_id'] && $check_field('add','refund_amount_'))"  @focus="set_refund_amount_()" :disabled="disabledObj['refund_amount__isDisabled']"></el-input>
					<div v-else-if="$check_field('get','refund_amount_')">{{form['refund_amount_']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','consumption_content') || $check_field('add','consumption_content') || $check_field('set','consumption_content')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="消费内容" prop="consumption_content">
					<el-input type="textarea" id="consumption_content" v-model="form['consumption_content']" placeholder="请输入消费内容"
						v-if="user_group === '管理员' || (form['refund_information_id'] && $check_field('set','consumption_content')) || (!form['refund_information_id'] && $check_field('add','consumption_content'))" :disabled="disabledObj['consumption_content_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','consumption_content')">{{form['consumption_content']}}</div>
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
				field: "refund_information_id",
				url_add: "~/api/refund_information/add?",
				url_set: "~/api/refund_information/set?",
				url_get_obj: "~/api/refund_information/get_obj?",
				url_upload: "~/api/refund_information/upload?",

				query: {
					"refund_information_id": 0,
				},

				form: {
					"patient": 0, // 患者
					"full_name":'', // 姓名
					"discharge_date_":'', // 出院日期
					"prepayment_amount":'', // 预缴金额
					"consumption_amount":0, // 消费金额
					"refund_amount_":'', // 退款金额
					"consumption_content":'', // 消费内容
					"refund_information_id": 0, // ID

				},
				disabledObj:{
					"patient_isDisabled": false,
					"full_name_isDisabled": false,
					"discharge_date__isDisabled": false,
					"prepayment_amount_isDisabled": false,
					"refund_amount__isDisabled": false,
					"consumption_content_isDisabled": false,
				},
				// 用户列表
				list_user_patient: [],

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
			set_refund_amount_(){
				this.form.refund_amount_ =this.form.prepayment_amount - this.form.consumption_amount
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
        if (this.form["discharge_date_"].indexOf("-")===-1){
          this.form["discharge_date_"] = this.$toTime(parseInt(this.form["discharge_date_"]),"yyyy-MM-dd")
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
				if(parseInt(this.form["discharge_date_"]) > 9999){
					this.form["discharge_date_"] = this.$toTime(parseInt(this.form["discharge_date_"]),"yyyy-MM-dd")
				}

			},

			is_view(){
				var bl = this.user_group == "管理员";

				if(!bl){
					bl = this.$check_action('/refund_information/table','add');
					console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
				}
				if(!bl){
					bl = this.$check_action('/refund_information/table','set');
					console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
				}
				if(!bl){
					bl = this.$check_action('/refund_information/view','add');
					console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
				}
				if(!bl){
					bl = this.$check_action('/refund_information/view','set');
					console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
				}
				if(!bl){
					bl = this.$check_action('/refund_information/view','get');
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
