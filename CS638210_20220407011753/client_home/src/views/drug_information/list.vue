<template>
	<div class="diy_list page_drug_information" id="drug_information_list">
		<div class="warp">
			<div class="container diy_list_container">
				<div class="diy_list_title">
					<div class="col">
						<span class="title">药品信息列表</span>
					</div>
				</div>
				<div class="row diy_list_search">
					<div class="col">
						<!-- 搜索栏 -->
						<div class="view">							
							<span class="diy_list_search_title">关键字搜索：</span>
							<b-form-input size="sm" class="mr-sm-2" placeholder="药品名称搜索" v-model="query['drug_name']" />
							<b-form-input size="sm" class="mr-sm-2" placeholder="药品类型搜索" v-model="query['drug_type']" />
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
								<b-dropdown text="药品类型" variant="outline-dark" left>
									<b-dropdown-item @click="filter_set('全部','drug_type')">全部</b-dropdown-item>
										<b-dropdown-item v-for="(o, i) in list_drug_type" :key="i" @click="filter_set(o['drug_type'],'drug_type')" >
												{{ o['drug_type'] }}
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
						<list_drug_information :list="list" />
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
	import list_drug_information from "@/components/diy/list_drug_information.vue";
	import diy_pager from "@/components/diy/diy_pager";
	import mixin from "@/mixins/page.js";

	export default {
		mixins: [mixin],
		components: {
			diy_pager,
			list_drug_information
		},
		data() {
			return {
				url_get_list: "~/api/drug_information/get_list?like=0",

				// 查询条件
				query: {
					keyword: "",
					page: 1,
					size: 10,
					"drug_name": "", // 药品名称
					"drug_type": "", // 药品类型
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
						name: "药品名称正序",
						value: "`drug_name` asc",
					},
					{
						name: "药品名称倒序",
						value: "`drug_name` desc",
					},
					{
						name: "药品类型正序",
						value: "`drug_type` asc",
					},
					{
						name: "药品类型倒序",
						value: "`drug_type` desc",
					},
				],
				// 药品类型列表
				"list_drug_type": [],
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
			 * 获取药品类型列表
			 */
			async get_list_drug_type() {
				var json = await this.$get("~/api/drug_category/get_list?");
				if (json.result) {
					this.list_drug_type = json.result.list;
				} else if (json.error) {
					console.log(json.error);
				}
			},

			/**
			 * 筛选
			 */
			filter_drug_type(o) {
				if (o == "全部") {
					this.query["drug_type"] = "";
				} else {
					this.query["drug_type"] = o;
				}
				this.search();
			},

			/**
			 * 重置
			 */
			reset() {
				this.query.drug_name = ""
				this.query.drug_type = ""
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
			 * 获取药品类型列表
			 */
			this.get_list_drug_type();
		}
	}
</script>

<style>
</style>
