<template>
	<div class="page_root" id="root_index">
		<div class="warp">
			<div class="container-fluid">
				<el-row>
					<el-col :span="4">
						<mm_label bg_color="bg_purple" icon="el-icon-user-solid" :url="url_user_count" unit="人"
								  title="用户数量"></mm_label>
					</el-col>
				</el-row>

				<el-row>
					<el-col v-if="user_group == '管理员' || $check_figure('/visit_form/table')" :span="8">
						<div class="card chart">
							<newBarChart v-if="bar_obj_visit_form.values.length > 0" id="bar_obj_visit_form" :vm="bar_obj_visit_form" :title="'就诊单统计'">
							</newBarChart>
							<div v-if="!bar_obj_visit_form.values.length">就诊单没有符合条件的数据</div>
						</div>
					</el-col>
					<el-col v-if="user_group == '管理员' || $check_figure('/drug_information/table')" :span="8">
						<div class="card chart">
							<pieChart v-if="list_drug_information.length" id="list_drug_information" :list="list_drug_information" :title="'药品信息统计'"></pieChart>
							<div v-if="!list_drug_information.length">药品信息没有符合条件的数据</div>
						</div>
					</el-col>
				</el-row>
			</div>
		</div>
	</div>
</template>
<script>
	import mixin from "@/mixins/page.js";
	import pieChart from "@/components/charts/pie_chart";
	import barChart from "@/components/charts/bar_chart";
	import newBarChart from "@/components/charts/new_bar_chart";
	import lineChart from "@/components/charts/line_chart";
	import newLineChart from "@/components/charts/new_line_chart";
	import mm_label from "@/components/mm_label.vue";
	export default {
		mixins: [mixin],
		name: "Home",
		components: {
			pieChart,
			barChart,
			newBarChart,
			lineChart,
			newLineChart,
			mm_label
		},
		data() {
			return {
				isAdmin: false,
				recognitionType: "",
				activeName: "third",
				bar_obj_visit_form: {
					names:[],
					xAxis: [],
					values:[]
				},
				list_drug_information: [],
				url_user_count: "~/api/user/count?",
			};
		},
		created() {
			this.getUserInfo();
			// 执行就诊单数据获取
			this.get_list_visit_form();
			// 执行药品信息数据获取
			this.get_list_drug_information();
			
			
		},
		mounted() {},
		methods: {
			async get_nickname(list,flag){
				if (flag) {
					for (let i=0;i<list.length;i++){
						await this.$get(
								"~/api/user/get_obj?user_id="+list[i],
								null,
								(json) => {
									if (json.result) {
										list[i] = json.result.obj.nickname;
									}
								});
					}
				}else {
					for (let i=0;i<list.length;i++){
						await this.$get(
								"~/api/user/get_obj?user_id="+list[i].name,
								null,
								(json) => {
									if (json.result) {
										list[i].name = json.result.obj.nickname;
									}
								});
					}
				}
			},
			// 获取就诊单统计柱状图
			async get_list_visit_form() {
				let name_list = [];
				let query_str = "";
				let group_by_value = "visit_time";
				let flag = false;
				let date_flag = "日期"
				name_list.push("药品金额品");
				query_str = query_str+"pharmaceutical_products"+","
				this.bar_obj_visit_form.names = name_list
				query_str = query_str.substr(0,query_str.length-1);
				let data = {};
				let user_group = this.$store.state.user.user_group;
				let user_id = this.$store.state.user.user_id;
				if (user_group!='管理员'){
					if (user_group=="医生"){
						data.doctor_number = user_id;
					}
					if (user_group=="患者"){
						data.patient = user_id;
					}
				}
				await this.$get(
						"~/api/visit_form/bar_group?field="+query_str+"&groupby="+group_by_value,
						data,
						(json) => {
							if (json.result) {
								let xAxis = [];
								let values = [];
								json.result.list.map((o) => {
									if (date_flag === "日期") {
										xAxis.push(this.$toTime(o[0] ,"yyyy-MM-dd"));
									}else if (date_flag === "时间") {
										xAxis.push(this.$toTime(o[0] ,"yyyy-MM-dd hh:mm:ss"));
									}else {
										xAxis.push(o[0]);
									}
									values.push(o.splice(1))
								});
								this.bar_obj_visit_form.xAxis = xAxis;
								this.bar_obj_visit_form.values = values;
							}
							if (flag){
								this.get_nickname(this.bar_obj_visit_form.xAxis,true);
							}
						});
			},
			// 获取药品信息统计图数据
			get_list_drug_information() {
				let data = {};
				let flag = false;
				this.$get("~/api/drug_information/list_group?groupby=drug_type", data, (json) => {
					if (json.result) {
						var list = json.result.list;
						this.list_drug_information = list.map((o) => {
							return {
								name: o[1],
								value: o[0]
							};
						});
						if (flag){
							this.get_nickname(this.list_drug_information,false);
						}
					}
				});
			},
				getUserInfo(){
					let userGroup = window.localStorage.getItem('user_group');
						if(userGroup){
						let _userGroup =JSON.parse(userGroup);
						let _info = JSON.parse(_userGroup.value);
						this.isAdmin = _info["user_group"] == "管理员" ? true : false;
					}
			}

		},
		computed:{
			recognitionHeight(){
				if(this.recognitionType === "face"){
					return "1070px"
				}else{
					return "1180px"
				}
			},
			recognitionUrl(){
					if(this.recognitionType === "face"){
					return "https://www.sk-ai.com/Experience/face-compare"
				}else{
					return "https://www.sk-ai.com/Experience/recognition?type="
				}
			}
		}
	};
</script>

<style scoped="scoped">
	.chart {
		display: block;
		width: 100%;
		height: 400px;
		padding: 1rem;
		position: relative;
	}

	.el-col {
		padding: 0.5rem;
	}

	.card {
		overflow: hidden;
	}
	
	.iframe_box ,.iframe_box_change{
		width: 100%;
		height: 1180px;
		position: relative;
		margin-top: 25px;
	}
	.iframe_box_change{
		height: 580px;
		padding-top: 50px;
	}
.iframe_box	.iframe_box_content, .iframe_box_change .iframe_box_content{
	width: 100%;
	height: 100%;
}
.iframe_box_top{
	position: absolute;
	top: 0;
	left: 0;
	width: 100%;
	height: 100px;
	font-size: 25px;
	line-height: 100px;
	background: #fff;
	z-index: 99999999;
	padding-left: 50px;
}
</style>
