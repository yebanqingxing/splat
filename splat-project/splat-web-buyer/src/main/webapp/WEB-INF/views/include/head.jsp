<%@ page contentType="text/html;charset=UTF-8" %><meta http-equiv="Content-Type" content="text/html;charset=utf-8" /><meta name="author" content="http://splat.com/"/>
<meta name="renderer" content="webkit"><meta http-equiv="X-UA-Compatible" content="IE=8,IE=9,IE=10" />
<meta http-equiv="Expires" content="0"><meta http-equiv="Cache-Control" content="no-cache">
<meta http-equiv="Cache-Control" content="no-store">
<script src="${ctxStatic}/bootstrap/assets/global/plugins/jquery.min.js" type="text/javascript"></script>
<link href="${ctxStatic}/bootstrap/2.3.1/css_cerulean/bootstrap.min.css" type="text/css" rel="stylesheet" />
<script src="${ctxStatic}/bootstrap/2.3.1/js/bootstrap.min.js" type="text/javascript"></script>
<link href="${ctxStatic}/jquery-select2/3.4/select2.min.css" rel="stylesheet" />
<script src="${ctxStatic}/jquery-select2/3.4/select2.min.js" type="text/javascript"></script>
<link href="${ctxStatic}/jquery-validation/1.11.0/jquery.validate.min.css" type="text/css" rel="stylesheet" />
<script src="${ctxStatic}/jquery-validation/1.11.0/jquery.validate.min.js" type="text/javascript"></script>
<link href="${ctxStatic}/jquery-jbox/2.3/Skins/Bootstrap/jbox.min.css" rel="stylesheet" />
<script src="${ctxStatic}/jquery-jbox/2.3/jquery.jBox-2.3.js" type="text/javascript"></script>
<script src="${ctxStatic}/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
<script src="${ctxStatic}/common/mustache.min.js" type="text/javascript"></script>
<link href="${ctxStatic}/common/splat.css" type="text/css" rel="stylesheet" />
<script src="${ctxStatic}/common/splat.min.js" type="text/javascript"></script>
<script type="text/javascript">var ctx = '${ctx}', ctxStatic='${ctxStatic}';</script>

<!-- BEGIN GLOBAL MANDATORY STYLES -->
<!-- link href="http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700&subset=all" rel="stylesheet" type="text/css"/ -->
<link href="${ctxStatic}/bootstrap/assets/global/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
<link href="${ctxStatic}/bootstrap/assets/global/plugins/simple-line-icons/simple-line-icons.min.css" rel="stylesheet" type="text/css"/>
<link href="${ctxStatic}/bootstrap/assets/global/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
<link href="${ctxStatic}/bootstrap/assets/global/plugins/uniform/css/uniform.default.css" rel="stylesheet" type="text/css"/>
<link href="${ctxStatic}/bootstrap/assets/global/plugins/bootstrap-switch/css/bootstrap-switch.min.css" rel="stylesheet" type="text/css"/>
<!-- END GLOBAL MANDATORY STYLES -->
<!-- BEGIN PAGE LEVEL PLUGIN STYLES -->
<link href="${ctxStatic}/bootstrap/assets/global/plugins/bootstrap-daterangepicker/daterangepicker-bs3.css" rel="stylesheet" type="text/css"/>
<link href="${ctxStatic}/bootstrap/assets/global/plugins/fullcalendar/fullcalendar.min.css" rel="stylesheet" type="text/css"/>
<link href="${ctxStatic}/bootstrap/assets/global/plugins/jqvmap/jqvmap/jqvmap.css" rel="stylesheet" type="text/css"/>
<!-- END PAGE LEVEL PLUGIN STYLES -->
<!-- BEGIN PAGE STYLES -->
<link href="${ctxStatic}/bootstrap/assets/admin/pages/css/tasks.css" rel="stylesheet" type="text/css"/>
<!-- END PAGE STYLES -->
<!-- BEGIN THEME STYLES -->
<link href="${ctxStatic}/bootstrap/assets/global/css/components.css" id="style_components" rel="stylesheet" type="text/css"/>
<link href="${ctxStatic}/bootstrap/assets/global/css/plugins.css" rel="stylesheet" type="text/css"/>
<link href="${ctxStatic}/bootstrap/assets/admin/layout/css/layout.css" rel="stylesheet" type="text/css"/>
<link href="${ctxStatic}/bootstrap/assets/admin/layout/css/themes/darkblue.css" rel="stylesheet" type="text/css" id="style_color"/>
<link href="${ctxStatic}/bootstrap/assets/admin/layout/css/custom.css" rel="stylesheet" type="text/css"/>
<!-- END THEME STYLES -->


<script src="${ctxStatic}/bootstrap/assets/global/plugins/jquery-migrate.min.js" type="text/javascript"></script>
<!-- IMPORTANT! Load jquery-ui.min.js before bootstrap.min.js to fix bootstrap tooltip conflict with jquery ui tooltip -->
<script src="${ctxStatic}/bootstrap/assets/global/plugins/jquery-ui/jquery-ui.min.js" type="text/javascript"></script>
<script src="${ctxStatic}/bootstrap/assets/global/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${ctxStatic}/bootstrap/assets/global/plugins/bootstrap-hover-dropdown/bootstrap-hover-dropdown.min.js" type="text/javascript"></script>
<script src="${ctxStatic}/bootstrap/assets/global/plugins/jquery-slimscroll/jquery.slimscroll.min.js" type="text/javascript"></script>
<script src="${ctxStatic}/bootstrap/assets/global/plugins/jquery.blockui.min.js" type="text/javascript"></script>
<script src="${ctxStatic}/bootstrap/assets/global/plugins/jquery.cokie.min.js" type="text/javascript"></script>
<script src="${ctxStatic}/bootstrap/assets/global/plugins/uniform/jquery.uniform.min.js" type="text/javascript"></script>
<script src="${ctxStatic}/bootstrap/assets/global/plugins/bootstrap-switch/js/bootstrap-switch.min.js" type="text/javascript"></script>
<!-- END CORE PLUGINS -->
<!-- BEGIN PAGE LEVEL PLUGINS -->
<script src="${ctxStatic}/bootstrap/assets/global/plugins/jqvmap/jqvmap/jquery.vmap.js" type="text/javascript"></script>
<script src="${ctxStatic}/bootstrap/assets/global/plugins/jqvmap/jqvmap/data/jquery.vmap.sampledata.js" type="text/javascript"></script>
<script src="${ctxStatic}/bootstrap/assets/global/plugins/flot/jquery.flot.min.js" type="text/javascript"></script>
<script src="${ctxStatic}/bootstrap/assets/global/plugins/flot/jquery.flot.resize.min.js" type="text/javascript"></script>
<script src="${ctxStatic}/bootstrap/assets/global/plugins/flot/jquery.flot.categories.min.js" type="text/javascript"></script>
<script src="${ctxStatic}/bootstrap/assets/global/plugins/jquery.pulsate.min.js" type="text/javascript"></script>
<script src="${ctxStatic}/bootstrap/assets/global/plugins/bootstrap-daterangepicker/moment.min.js" type="text/javascript"></script>
<script src="${ctxStatic}/bootstrap/assets/global/plugins/bootstrap-daterangepicker/daterangepicker.js" type="text/javascript"></script>
<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script src="${ctxStatic}/bootstrap/assets/global/scripts/metronic.js" type="text/javascript"></script>
<script src="${ctxStatic}/bootstrap/assets/admin/layout/scripts/layout.js" type="text/javascript"></script>
<script src="${ctxStatic}/bootstrap/assets/admin/layout/scripts/quick-sidebar.js" type="text/javascript"></script>
<script src="${ctxStatic}/bootstrap/assets/admin/layout/scripts/demo.js" type="text/javascript"></script>
<script src="${ctxStatic}/bootstrap/assets/admin/pages/scripts/index.js" type="text/javascript"></script>
<script src="${ctxStatic}/bootstrap/assets/admin/pages/scripts/tasks.js" type="text/javascript"></script>
<!-- js -->
<%-- <script type="text/javascript" src="${ctxStatic}/js/jquery-1.8.3.min.js"></script> --%>
<%-- <script type="text/javascript" src="${ctxStatic}/js/order.js"></script> --%>
<%-- <script type="text/javascript" src="${ctxStatic}/js/jquery-ui.min.js"></script> --%>
<%-- <script type="text/javascript" src="${ctxStatic}/js/addpolicy.js"></script>
<script type="text/javascript" src="${ctxStatic}/js/aircity.js"></script>
<script type="text/javascript" src="${ctxStatic}/js/banner.js"></script>
<script type="text/javascript" src="${ctxStatic}/js/iframe.js"></script>
<script type="text/javascript" src="${ctxStatic}/js/j.dimensions.js"></script>
<script type="text/javascript" src="${ctxStatic}/js/j.suggest.js"></script>
<script type="text/javascript" src="${ctxStatic}/js/modify.js"></script>
<script type="text/javascript" src="${ctxStatic}/js/ordersuc.js"></script>
<script type="text/javascript" src="${ctxStatic}/js/pnradd.js"></script>
<script type="text/javascript" src="${ctxStatic}/js/touchslider.js"></script> --%>
<%-- <script type="text/javascript" src="${ctxStatic}/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${ctxStatic}/js/order.js"></script>
<script type="text/javascript" src="${ctxStatic}/js/addpolicy.js"></script>
<script type="text/javascript" src="${ctxStatic}/js/aircity.js"></script>
<script type="text/javascript" src="${ctxStatic}/js/banner.js"></script>
<script type="text/javascript" src="${ctxStatic}/js/iframe.js"></script>
<script type="text/javascript" src="${ctxStatic}/js/j.dimensions.js"></script>
<script type="text/javascript" src="${ctxStatic}/js/j.suggest.js"></script>
<script type="text/javascript" src="${ctxStatic}/js/modify.js"></script>
<script type="text/javascript" src="${ctxStatic}/js/ordersuc.js"></script>
<script type="text/javascript" src="${ctxStatic}/js/pnradd.js"></script>
<script type="text/javascript" src="${ctxStatic}/js/touchslider.js"></script> --%>
<!-- css -->
<link rel="stylesheet" type="text/css" href="${ctxStatic}/css/sign.css">
<link rel="stylesheet" type="text/css" href="${ctxStatic}/css/jquery-ui.min.css">
<link rel="stylesheet" type="text/css" href="${ctxStatic}/css/addnav.css">
<link rel="stylesheet" type="text/css" href="${ctxStatic}/css/style.css">
<link rel="stylesheet" type="text/css" href="${ctxStatic}/css/reset.css">
<link rel="stylesheet" type="text/css" href="${ctxStatic}/css/order.css">
<link rel="stylesheet" type="text/css" href="${ctxStatic}/css/backpur.css">
<link rel="stylesheet" type="text/css" href="${ctxStatic}/css/bannerstyle.css">
<link rel="stylesheet" type="text/css" href="${ctxStatic}/css/basics.css">
<link rel="stylesheet" type="text/css" href="${ctxStatic}/css/pading.css">
<link rel="stylesheet" type="text/css" href="${ctxStatic}/css/pnrdetail.css">
<link rel="stylesheet" type="text/css" href="${ctxStatic}/css/pnrimport.css">
<link rel="stylesheet" type="text/css" href="${ctxStatic}/css/policy.css">
<link rel="stylesheet" type="text/css" href="${ctxStatic}/css/query.css">

















