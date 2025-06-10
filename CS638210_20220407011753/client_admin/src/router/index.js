import Vue from 'vue';
import VueRouter from 'vue-router';
import index from '../views/index.vue';
import login from '../views/login.vue';
import forgot from '../views/forgot.vue';
Vue.use(VueRouter)

const routes = [
	// 主页
	{
		path: '/',
		name: 'index',
		component: index,
		meta: {
			index: 0,
			title: '首页'
		}
	},

	// 登录
	{
		path: '/login',
		name: 'login',
		component: login,
		meta: {
			index: 0,
			title: '登录'
		}
	},

	// 忘记密码
	{
		path: '/forgot',
		name: "forgot",
		component: forgot,
		meta: {
			index: 0,
			title: '忘记密码'
		}
	},

	// 修改密码
	{
		path: '/user/password',
		name: "password",
		component: () => import("../views/user/password.vue"),
		meta: {
			index: 0,
			title: '修改密码'
		}
	},

	// 视频播放页
	{
		path: "/media/video",
		name: "video",
		component: () => import('../views/media/video.vue'),
		meta: {
			index: 0,
			title: "视频"
		}
	},

	// 音频播放页
	{
		path: "/media/audio",
		name: "audio",
		component: () => import('../views/media/audio.vue'),
		meta: {
			index: 0,
			title: "音频"
		}
	},
	// 权限路由
	{
		path: '/auth/table',
		name: 'auth_table',
		component: () => import('../views/nav/table.vue'),
		meta: {
			index: 0,
			title: '权限列表'
		}
	},
	{
		path: '/auth/view',
		name: 'auth_view',
		component: () => import('../views/nav/view.vue'),
		meta: {
			index: 0,
			title: '权限详情'
		}
	},

	// 友情链接路由
	// {
	// 	path: '/link/table',
	// 	name: 'link_table',
	// 	component: () => import('../views/link/table.vue'),
	// 	meta: {
	// 		index: 0,
	// 		title: '链接列表'
	// 	}
	// },
	// {
	// 	path: '/link/view',
	// 	name: 'link_view',
	// 	component: () => import('../views/link/view.vue'),
	// 	meta: {
	// 		index: 0,
	// 		title: '链接详情'
	// 	}
	// },

	// 轮播图路由
	{
		path: '/slides/table',
		name: 'slides_table',
		component: () => import('../views/slides/table.vue'),
		meta: {
			index: 0,
			title: '轮播图列表'
		}
	},
	{
		path: '/slides/view',
		name: 'slides_view',
		component: () => import('../views/slides/view.vue'),
		meta: {
			index: 0,
			title: '轮播图详情'
		}
	},
	// 文章路由
	{
		path: '/article/table',
		name: 'article_table',
		component: () => import('../views/article/table.vue'),
		meta: {
			index: 0,
			title: '文章列表'
		}
	},
	{
		path: '/article/view',
		name: 'article_view',
		component: () => import('../views/article/view.vue'),
		meta: {
			index: 0,
			title: '文章详情'
		}
	},

	// 文章分类路由
	{
		path: '/article_type/table',
		name: 'article_type_table',
		component: () => import('../views/article_type/table.vue'),
		meta: {
			index: 0,
			title: '文章分类列表'
		}
	},
	{
		path: '/article_type/view',
		name: 'article_type_view',
		component: () => import('../views/article_type/view.vue'),
		meta: {
			index: 0,
			title: '文章分类详情'
		}
	},

	// 广告路由
	// {
	// 	path: '/ad/table',
	// 	name: 'ad_table',
	// 	component: () => import('../views/ad/table.vue'),
	// 	meta: {
	// 		index: 0,
	// 		title: '广告列表'
	// 	}
	// },
	// {
	// 	path: '/ad/view',
	// 	name: 'ad_view',
	// 	component: () => import('../views/ad/view.vue'),
	// 	meta: {
	// 		index: 0,
	// 		title: '广告详情'
	// 	}
	// },
	// 公告路由
	{
		path: '/notice/table',
		name: 'notice_table',
		component: () => import('../views/notice/table.vue'),
		meta: {
			index: 0,
			title: '公告列表'
		}
	},
	{
		path: '/notice/view',
		name: 'notice_view',
		component: () => import('../views/notice/view.vue'),
		meta: {
			index: 0,
			title: '公告详情'
		}
	},
	// 评论路由
	{
		path: '/comment/table',
		name: 'comment_table',
		component: () => import('../views/comment/table.vue'),
		meta: {
			index: 0,
			title: '评论列表'
		}
	},
	{
		path: '/comment/view',
		name: 'comment_view',
		component: () => import('../views/comment/view.vue'),
		meta: {
			index: 0,
			title: '评论详情'
		}
	},
	// 患者路由
	{
		path: '/patient/table',
		name: 'patient_table',
		component: () => import('../views/patient/table.vue')
	},
	{
		path: '/patient/view',
		name: 'patient_view',
		component: () => import('../views/patient/view.vue')
	},
	// 医生路由
	{
		path: '/doctor/table',
		name: 'doctor_table',
		component: () => import('../views/doctor/table.vue')
	},
	{
		path: '/doctor/view',
		name: 'doctor_view',
		component: () => import('../views/doctor/view.vue')
	},
	// 医生信息路由
	{
		path: '/doctor_information/table',
		name: 'doctor_information_table',
		component: () => import('../views/doctor_information/table.vue')
	},
	{
		path: '/doctor_information/view',
		name: 'doctor_information_view',
		component: () => import('../views/doctor_information/view.vue')
	},
	// 科室管理路由
	{
		path: '/department_management/table',
		name: 'department_management_table',
		component: () => import('../views/department_management/table.vue')
	},
	{
		path: '/department_management/view',
		name: 'department_management_view',
		component: () => import('../views/department_management/view.vue')
	},
	// 挂号预约路由
	{
		path: '/registration_reservation/table',
		name: 'registration_reservation_table',
		component: () => import('../views/registration_reservation/table.vue')
	},
	{
		path: '/registration_reservation/view',
		name: 'registration_reservation_view',
		component: () => import('../views/registration_reservation/view.vue')
	},
	// 就诊单路由
	{
		path: '/visit_form/table',
		name: 'visit_form_table',
		component: () => import('../views/visit_form/table.vue')
	},
	{
		path: '/visit_form/view',
		name: 'visit_form_view',
		component: () => import('../views/visit_form/view.vue')
	},
	// 住院办理路由
	{
		path: '/hospitalization_handling/table',
		name: 'hospitalization_handling_table',
		component: () => import('../views/hospitalization_handling/table.vue')
	},
	{
		path: '/hospitalization_handling/view',
		name: 'hospitalization_handling_view',
		component: () => import('../views/hospitalization_handling/view.vue')
	},
	// 药品信息路由
	{
		path: '/drug_information/table',
		name: 'drug_information_table',
		component: () => import('../views/drug_information/table.vue')
	},
	{
		path: '/drug_information/view',
		name: 'drug_information_view',
		component: () => import('../views/drug_information/view.vue')
	},
	// 住院通知路由
	{
		path: '/hospitalization_notice/table',
		name: 'hospitalization_notice_table',
		component: () => import('../views/hospitalization_notice/table.vue')
	},
	{
		path: '/hospitalization_notice/view',
		name: 'hospitalization_notice_view',
		component: () => import('../views/hospitalization_notice/view.vue')
	},
	// 药品类别路由
	{
		path: '/drug_category/table',
		name: 'drug_category_table',
		component: () => import('../views/drug_category/table.vue')
	},
	{
		path: '/drug_category/view',
		name: 'drug_category_view',
		component: () => import('../views/drug_category/view.vue')
	},
	// 退费信息路由
	{
		path: '/refund_information/table',
		name: 'refund_information_table',
		component: () => import('../views/refund_information/table.vue')
	},
	{
		path: '/refund_information/view',
		name: 'refund_information_view',
		component: () => import('../views/refund_information/view.vue')
	},
	// 检查信息路由
	{
		path: '/check_information/table',
		name: 'check_information_table',
		component: () => import('../views/check_information/table.vue')
	},
	{
		path: '/check_information/view',
		name: 'check_information_view',
		component: () => import('../views/check_information/view.vue')
	},

	// 用户路由
	{
		path: '/user/table',
		name: 'user_table',
		component: () => import('../views/user/table.vue'),
		meta: {
			index: 0,
			title: '用户列表'
		}
	},
	{
		path: '/user/view',
		name: 'user_view',
		component: () => import('../views/user/view.vue'),
		meta: {
			index: 0,
			title: '用户详情'
		}
	},
	{
		path: '/user/info',
		name: 'user_info',
		component: () => import('../views/user/info.vue'),
		meta: {
			index: 0,
			title: '个人信息'
		}
	},
	// 用户组路由
	{
		path: '/user_group/table',
		name: 'user_group_table',
		component: () => import('../views/user_group/table.vue'),
		meta: {
			index: 0,
			title: '用户组列表'
		}
	},
	{
		path: '/user_group/view',
		name: 'user_group_view',
		component: () => import('../views/user_group/view.vue'),
		meta: {
			index: 0,
			title: '用户组详情'
		}
	}
]

const router = new VueRouter({
	mode: 'history',
	base: process.env.BASE_URL,
	routes
})

router.beforeEach((to, from, next) => {
	let token = to.query.token;
	if (token){
		$.db.set("token",token,120);
	}
	next();
})

router.afterEach((to, from, next) => {
	let title = "医疗管理系统-admin";
	document.title = title;
})

export default router
