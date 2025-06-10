<template>
	<div class="diy_list page_doctor_information" id="doctor_information_list">
		<div class="warp">
			<div class="container diy_list_container">
				<div class="diy_list_title">
					<div class="col">
						<span class="title">医生信息列表</span>
					</div>
				</div>
				<div class="row diy_list_search">
					<div class="col">
						<!-- 搜索栏 -->
						<div class="view">							
							<span class="diy_list_search_title">关键字搜索：</span>
							<b-form-input size="sm" class="mr-sm-2" placeholder="医生姓名搜索" v-model="query['name_of_doctor']" />
							<b-form-input size="sm" class="mr-sm-2" placeholder="科室名称搜索" v-model="query['department_name']" />
							<b-button size="sm" @click="search()" >
								<b-icon icon="search"/>
							</b-button>
						</div>
						<!-- /搜索栏 -->
					</div>
				</div>
				<div class="diy_list_select_box">
					<span class="diy_list_select_title">下拉搜索：</span>
						<div class="diy_list_dropdown_box">
						<div class="col">
							<!-- 筛选 -->
							<div class="view">
								<b-dropdown text="科室名称" variant="outline-dark" left>
									<b-dropdown-item @click="filter_set('全部','department_name')">全部</b-dropdown-item>
										<b-dropdown-item v-for="(o, i) in list_department_name" :key="i" @click="filter_set(o['department_name'],'department_name')" >
												{{ o['department_name'] }}
										</b-dropdown-item>
								</b-dropdown>
							</div>
							<!-- /筛选 -->
						</div>
					</div>
					<div class="diy_list_sort_box">
						<div class="col">
							<!-- 排序 -->
							<div class="view">
								<b-dropdown text="排序" variant="outline-dark" left>
										<b-dropdown-item v-for="(o, i) in list_sort" :key="i" @click="set_sort(o)" >
												{{ o.name }}
										</b-dropdown-item>
								</b-dropdown>
							</div>
							<!--/排序 -->
						</div>
					</div>
				</div>
				<div class="row diy_list_box">
					<div class="col">
						<!-- 列表 -->
						<list_doctor_information :list="list" />
						<!-- /列表 -->
					</div>
				</div>
				<div class="row diy_list_page_box">
					<div class="col overflow-auto flex_cc">
						<!-- 分页器 -->
						<diy_pager v-model="query['page']" :size="query['size']" :count="count" v-on:toPage="toPage" v-on:toSize="toSize" ></diy_pager>
						<!-- /分页器 -->
					</div>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
	import list_doctor_information from "@/components/diy/list_doctor_information.vue";
	import diy_pager from "@/components/diy/diy_pager";
	import mixin from "@/mixins/page.js";

	export default {
		mixins: [mixin],
		components: {
			diy_pager,
			list_doctor_information
		},
		data() {
			return {
				url_get_list: "~/api/doctor_information/get_list?like=0",

				// 查询条件
				query: {
					keyword: "",
					page: 1,
					size: 10,
					"name_of_doctor": "", // 医生姓名
					"department_name": "", // 科室名称
				},

				// 排序内容
				list_sort: [{
						name: "创建时间从高到低",
						value: "`create_time` desc",
					},
					{
						name: "创建时间从低到高",
						value: "`create_time` asc",
					},
					{
						name: "更新时间从高到低",
						value: "`update_time` desc",
					},
					{
						name: "更新时间从低到高",
						value: "`update_time` asc",
					},
					{
						name: "医生姓名正序",
						value: "`name_of_doctor` asc",
					},
					{
						name: "医生姓名倒序",
						value: "`name_of_doctor` desc",
					},
					{
						name: "科室名称正序",
						value: "`department_name` asc",
					},
					{
						name: "科室名称倒序",
						value: "`department_name` desc",
					},
				],
				// 科室名称列表
				"list_department_name": [],
			}
		},
		methods: {
			/**
			 * 筛选选择
			 */
			filter_set(o,key) {
			    if (o == "全部") {
			        this.query[key] = "";
			    } else {
			        this.query[key] = o;
			    }
			    this.search();
			},

			/**
			 * 排序
			 */
			set_sort(o) {
			    this.query.orderby = o.value;
			    this.search();
			},
			/**
			 * 获取科室名称列表
			 */
			async get_list_department_name() {
				var json = await this.$get("~/api/department_management/get_list?");
				if (json.result) {
					this.list_department_name = json.result.list;
				} else if (json.error) {
					console.log(json.error);
				}
			},

			/**
			 * 筛选
			 */
			filter_department_name(o) {
				if (o == "全部") {
					this.query["department_name"] = "";
				} else {
					this.query["department_name"] = o;
				}
				this.search();
			},

			/**
			 * 重置
			 */
			reset() {
				this.query.name_of_doctor = ""
				this.query.department_name = ""
				this.search();
			},

			// 返回条数
			toSize(i){
				this.query.size = i;
				this.first();
			},

			// 返回页数
			toPage(i){
				this.query.page = i;
				this.first();
			},
		},
		computed: {
		},
		created() {
			/**
			 * 获取科室名称列表
			 */
			this.get_list_department_name();
		}
	}
</script>

<style>
</style>
