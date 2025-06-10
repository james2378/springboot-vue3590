<template>
  <div class="page_search">
	<div class="warp">
	  <div class="container">
		<div class="row">
		  <div class="col-12">
			<div class="card_result_search">
			  <div class="title">搜索结果</div>
				<!-- 文章搜索结果 -->
			  <list_result_search
				:list="result_article"
				title="医院资讯"
				source_table="article"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/patient/list', 'get')"
				:list="result_patient_full_name"
				title="患者姓名"
				source_table="patient"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/patient/list', 'get')"
				:list="result_patient_gender"
				title="患者性别"
				source_table="patient"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/doctor/list', 'get')"
				:list="result_doctor_name_of_doctor"
				title="医生医生姓名"
				source_table="doctor"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/doctor_information/list', 'get')"
				:list="result_doctor_information_name_of_doctor"
				title="医生信息医生姓名"
				source_table="doctor_information"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/doctor_information/list', 'get')"
				:list="result_doctor_information_department_name"
				title="医生信息科室名称"
				source_table="doctor_information"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/department_management/list', 'get')"
				:list="result_department_management_department_name"
				title="科室管理科室名称"
				source_table="department_management"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/registration_reservation/list', 'get')"
				:list="result_registration_reservation_name_of_doctor"
				title="挂号预约医生姓名"
				source_table="registration_reservation"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/registration_reservation/list', 'get')"
				:list="result_registration_reservation_full_name"
				title="挂号预约姓名"
				source_table="registration_reservation"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/registration_reservation/list', 'get')"
				:list="result_registration_reservation_time_of_appointment"
				title="挂号预约预约时间"
				source_table="registration_reservation"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/visit_form/list', 'get')"
				:list="result_visit_form_name_of_doctor"
				title="就诊单医生姓名"
				source_table="visit_form"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/visit_form/list', 'get')"
				:list="result_visit_form_full_name"
				title="就诊单姓名"
				source_table="visit_form"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/visit_form/list', 'get')"
				:list="result_visit_form_visit_time"
				title="就诊单就诊时间"
				source_table="visit_form"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/hospitalization_handling/list', 'get')"
				:list="result_hospitalization_handling_full_name"
				title="住院办理姓名"
				source_table="hospitalization_handling"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/hospitalization_handling/list', 'get')"
				:list="result_hospitalization_handling_check_in_time"
				title="住院办理入住时间"
				source_table="hospitalization_handling"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/drug_information/list', 'get')"
				:list="result_drug_information_drug_name"
				title="药品信息药品名称"
				source_table="drug_information"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/drug_information/list', 'get')"
				:list="result_drug_information_drug_type"
				title="药品信息药品类型"
				source_table="drug_information"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/hospitalization_notice/list', 'get')"
				:list="result_hospitalization_notice_full_name"
				title="住院通知姓名"
				source_table="hospitalization_notice"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/refund_information/list', 'get')"
				:list="result_refund_information_full_name"
				title="退费信息姓名"
				source_table="refund_information"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/check_information/list', 'get')"
				:list="result_check_information_full_name"
				title="检查信息姓名"
				source_table="check_information"
			  ></list_result_search>
			</div>
		  </div>
		</div>
	  </div>
	</div>
  </div>
</template>

<script>
import mixin from "../../mixins/page.js";
import list_result_search from "../../components/diy/list_result_search.vue";

export default {
  mixins: [mixin],
  data() {
	return {
	  "query": {
		word: "",
	  },
	  "result_article": [],
			"result_patient_full_name":[],
			"result_patient_gender":[],
			"result_doctor_name_of_doctor":[],
			"result_doctor_information_name_of_doctor":[],
			"result_doctor_information_department_name":[],
			"result_department_management_department_name":[],
			"result_registration_reservation_name_of_doctor":[],
			"result_registration_reservation_full_name":[],
			"result_registration_reservation_time_of_appointment":[],
			"result_visit_form_name_of_doctor":[],
			"result_visit_form_full_name":[],
			"result_visit_form_visit_time":[],
			"result_hospitalization_handling_full_name":[],
			"result_hospitalization_handling_check_in_time":[],
			"result_drug_information_drug_name":[],
			"result_drug_information_drug_type":[],
			"result_hospitalization_notice_full_name":[],
			"result_refund_information_full_name":[],
			"result_check_information_full_name":[],
	};
  },
  methods: {
	/**
	 * 获取文章
	 */
	get_article() {
	  this.$get("~/api/article/get_list?like=0", { page: 1, size: 10, title: this.query.word }, (json) => {
		if (json.result) {
		  this.result_article = json.result.list;
		}
	  });
	},
	/**
	 * 获取full_name
	 */
	get_patient_full_name(){
		this.$get("~/api/patient/get_list?like=0", { page: 1, size: 10, "full_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_patient_full_name = json.result.list;
			result_patient_full_name.map(o => o.title = o['full_name'])
	  			this.result_patient_full_name = result_patient_full_name
		 	}
		});
	},
	/**
	 * 获取gender
	 */
	get_patient_gender(){
		this.$get("~/api/patient/get_list?like=0", { page: 1, size: 10, "gender": this.query.word }, (json) => {
		  if (json.result) {
			var result_patient_gender = json.result.list;
			result_patient_gender.map(o => o.title = o['gender'])
	  			this.result_patient_gender = result_patient_gender
		 	}
		});
	},
	/**
	 * 获取name_of_doctor
	 */
	get_doctor_name_of_doctor(){
		this.$get("~/api/doctor/get_list?like=0", { page: 1, size: 10, "name_of_doctor": this.query.word }, (json) => {
		  if (json.result) {
			var result_doctor_name_of_doctor = json.result.list;
			result_doctor_name_of_doctor.map(o => o.title = o['name_of_doctor'])
	  			this.result_doctor_name_of_doctor = result_doctor_name_of_doctor
		 	}
		});
	},
	/**
	 * 获取name_of_doctor
	 */
	get_doctor_information_name_of_doctor(){
		this.$get("~/api/doctor_information/get_list?like=0", { page: 1, size: 10, "name_of_doctor": this.query.word }, (json) => {
		  if (json.result) {
			var result_doctor_information_name_of_doctor = json.result.list;
			result_doctor_information_name_of_doctor.map(o => o.title = o['name_of_doctor'])
	  			this.result_doctor_information_name_of_doctor = result_doctor_information_name_of_doctor
		 	}
		});
	},
	/**
	 * 获取department_name
	 */
	get_doctor_information_department_name(){
		this.$get("~/api/doctor_information/get_list?like=0", { page: 1, size: 10, "department_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_doctor_information_department_name = json.result.list;
			result_doctor_information_department_name.map(o => o.title = o['department_name'])
	  			this.result_doctor_information_department_name = result_doctor_information_department_name
		 	}
		});
	},
	/**
	 * 获取department_name
	 */
	get_department_management_department_name(){
		this.$get("~/api/department_management/get_list?like=0", { page: 1, size: 10, "department_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_department_management_department_name = json.result.list;
			result_department_management_department_name.map(o => o.title = o['department_name'])
	  			this.result_department_management_department_name = result_department_management_department_name
		 	}
		});
	},
	/**
	 * 获取name_of_doctor
	 */
	get_registration_reservation_name_of_doctor(){
		this.$get("~/api/registration_reservation/get_list?like=0", { page: 1, size: 10, "name_of_doctor": this.query.word }, (json) => {
		  if (json.result) {
			var result_registration_reservation_name_of_doctor = json.result.list;
			result_registration_reservation_name_of_doctor.map(o => o.title = o['name_of_doctor'])
	  			this.result_registration_reservation_name_of_doctor = result_registration_reservation_name_of_doctor
		 	}
		});
	},
	/**
	 * 获取full_name
	 */
	get_registration_reservation_full_name(){
		this.$get("~/api/registration_reservation/get_list?like=0", { page: 1, size: 10, "full_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_registration_reservation_full_name = json.result.list;
			result_registration_reservation_full_name.map(o => o.title = o['full_name'])
	  			this.result_registration_reservation_full_name = result_registration_reservation_full_name
		 	}
		});
	},
	/**
	 * 获取time_of_appointment
	 */
	get_registration_reservation_time_of_appointment(){
		this.$get("~/api/registration_reservation/get_list?like=0", { page: 1, size: 10, "time_of_appointment": this.query.word }, (json) => {
		  if (json.result) {
			var result_registration_reservation_time_of_appointment = json.result.list;
			result_registration_reservation_time_of_appointment.map(o => o.title = o['time_of_appointment'])
	  			this.result_registration_reservation_time_of_appointment = result_registration_reservation_time_of_appointment
		 	}
		});
	},
	/**
	 * 获取name_of_doctor
	 */
	get_visit_form_name_of_doctor(){
		this.$get("~/api/visit_form/get_list?like=0", { page: 1, size: 10, "name_of_doctor": this.query.word }, (json) => {
		  if (json.result) {
			var result_visit_form_name_of_doctor = json.result.list;
			result_visit_form_name_of_doctor.map(o => o.title = o['name_of_doctor'])
	  			this.result_visit_form_name_of_doctor = result_visit_form_name_of_doctor
		 	}
		});
	},
	/**
	 * 获取full_name
	 */
	get_visit_form_full_name(){
		this.$get("~/api/visit_form/get_list?like=0", { page: 1, size: 10, "full_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_visit_form_full_name = json.result.list;
			result_visit_form_full_name.map(o => o.title = o['full_name'])
	  			this.result_visit_form_full_name = result_visit_form_full_name
		 	}
		});
	},
	/**
	 * 获取visit_time
	 */
	get_visit_form_visit_time(){
		this.$get("~/api/visit_form/get_list?like=0", { page: 1, size: 10, "visit_time": this.query.word }, (json) => {
		  if (json.result) {
			var result_visit_form_visit_time = json.result.list;
			result_visit_form_visit_time.map(o => o.title = o['visit_time'])
	  			this.result_visit_form_visit_time = result_visit_form_visit_time
		 	}
		});
	},
	/**
	 * 获取full_name
	 */
	get_hospitalization_handling_full_name(){
		this.$get("~/api/hospitalization_handling/get_list?like=0", { page: 1, size: 10, "full_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_hospitalization_handling_full_name = json.result.list;
			result_hospitalization_handling_full_name.map(o => o.title = o['full_name'])
	  			this.result_hospitalization_handling_full_name = result_hospitalization_handling_full_name
		 	}
		});
	},
	/**
	 * 获取check_in_time
	 */
	get_hospitalization_handling_check_in_time(){
		this.$get("~/api/hospitalization_handling/get_list?like=0", { page: 1, size: 10, "check_in_time": this.query.word }, (json) => {
		  if (json.result) {
			var result_hospitalization_handling_check_in_time = json.result.list;
			result_hospitalization_handling_check_in_time.map(o => o.title = o['check_in_time'])
	  			this.result_hospitalization_handling_check_in_time = result_hospitalization_handling_check_in_time
		 	}
		});
	},
	/**
	 * 获取drug_name
	 */
	get_drug_information_drug_name(){
		this.$get("~/api/drug_information/get_list?like=0", { page: 1, size: 10, "drug_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_drug_information_drug_name = json.result.list;
			result_drug_information_drug_name.map(o => o.title = o['drug_name'])
	  			this.result_drug_information_drug_name = result_drug_information_drug_name
		 	}
		});
	},
	/**
	 * 获取drug_type
	 */
	get_drug_information_drug_type(){
		this.$get("~/api/drug_information/get_list?like=0", { page: 1, size: 10, "drug_type": this.query.word }, (json) => {
		  if (json.result) {
			var result_drug_information_drug_type = json.result.list;
			result_drug_information_drug_type.map(o => o.title = o['drug_type'])
	  			this.result_drug_information_drug_type = result_drug_information_drug_type
		 	}
		});
	},
	/**
	 * 获取full_name
	 */
	get_hospitalization_notice_full_name(){
		this.$get("~/api/hospitalization_notice/get_list?like=0", { page: 1, size: 10, "full_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_hospitalization_notice_full_name = json.result.list;
			result_hospitalization_notice_full_name.map(o => o.title = o['full_name'])
	  			this.result_hospitalization_notice_full_name = result_hospitalization_notice_full_name
		 	}
		});
	},
	/**
	 * 获取full_name
	 */
	get_refund_information_full_name(){
		this.$get("~/api/refund_information/get_list?like=0", { page: 1, size: 10, "full_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_refund_information_full_name = json.result.list;
			result_refund_information_full_name.map(o => o.title = o['full_name'])
	  			this.result_refund_information_full_name = result_refund_information_full_name
		 	}
		});
	},
	/**
	 * 获取full_name
	 */
	get_check_information_full_name(){
		this.$get("~/api/check_information/get_list?like=0", { page: 1, size: 10, "full_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_check_information_full_name = json.result.list;
			result_check_information_full_name.map(o => o.title = o['full_name'])
	  			this.result_check_information_full_name = result_check_information_full_name
		 	}
		});
	},

  },
  components: { list_result_search },
	created(){
    this.query.word = this.$route.query.word || "";
  },
  mounted() {
	this.get_article();
		this.get_patient_full_name();
		this.get_patient_gender();
		this.get_doctor_name_of_doctor();
		this.get_doctor_information_name_of_doctor();
		this.get_doctor_information_department_name();
		this.get_department_management_department_name();
		this.get_registration_reservation_name_of_doctor();
		this.get_registration_reservation_full_name();
		this.get_registration_reservation_time_of_appointment();
		this.get_visit_form_name_of_doctor();
		this.get_visit_form_full_name();
		this.get_visit_form_visit_time();
		this.get_hospitalization_handling_full_name();
		this.get_hospitalization_handling_check_in_time();
		this.get_drug_information_drug_name();
		this.get_drug_information_drug_type();
		this.get_hospitalization_notice_full_name();
		this.get_refund_information_full_name();
		this.get_check_information_full_name();
  },
  watch: {
	$route() {
	  $.push(this.query, this.$route.query);
	  this.get_article();
	  this.get_patient_full_name();
	  this.get_patient_gender();
	  this.get_doctor_name_of_doctor();
	  this.get_doctor_information_name_of_doctor();
	  this.get_doctor_information_department_name();
	  this.get_department_management_department_name();
	  this.get_registration_reservation_name_of_doctor();
	  this.get_registration_reservation_full_name();
	  this.get_registration_reservation_time_of_appointment();
	  this.get_visit_form_name_of_doctor();
	  this.get_visit_form_full_name();
	  this.get_visit_form_visit_time();
	  this.get_hospitalization_handling_full_name();
	  this.get_hospitalization_handling_check_in_time();
	  this.get_drug_information_drug_name();
	  this.get_drug_information_drug_type();
	  this.get_hospitalization_notice_full_name();
	  this.get_refund_information_full_name();
	  this.get_check_information_full_name();
	},
  },
};
</script>

<style scoped>
.card_search {
  text-align: center;
}
.card_result_search>.title {
  text-align: center;
  padding: 10px 0;
}
</style>
