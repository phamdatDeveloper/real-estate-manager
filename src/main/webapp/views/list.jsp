<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"
	prefix="dec"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>Building List</title>
</head>

<body>
	<div class="main-content">
		<div class="main-content-inner">
			<div class="breadcrumbs" id="breadcrumbs">
				<script type="text/javascript">
					try {
						ace.settings.check('breadcrumbs', 'fixed')
					} catch (e) {
					}
				</script>

				<ul class="breadcrumb">
					<li><i class="ace-icon fa fa-home home-icon"></i> <a href="#">Home</a>
					</li>
					<li class="active">Dashboard</li>
				</ul>
				<!-- /.breadcrumb -->
			</div>

			<div class="page-content">

				<div class="page-header">
					<h1>
						Dashboard <small> <i
							class="ace-icon fa fa-angle-double-right"></i> overview &amp;
							stats
						</small>
					</h1>
				</div>
				<!-- /.page-header -->
				<div class="row">
					<div class="widget-box">
						<div class="widget-header">
							<h4 class="widget-title">Tìm kiếm</h4>
							<div class="widget-toolbar">
								<a href="#" data-action="collapse"> <i
									class="ace-icon fa fa-chevron-up"></i>
								</a>
							</div>
						</div>
						<div class="widget-body">
							<div class="widget-main">
								<div class="row">
									<form id="buildingForm" method="get"
										action='<c:url value="/building-list?action=LIST"/>'>
										<div class="col-xs-12" style="margin-top: 20px;">
											<!-- PAGE CONTENT BEGINS -->
											<div class="col-sm-6">
												<div>
													<label for="name">Tên tòa nhà </label> <input type="text"
														id="name" name="name" class="form-control">
												</div>
											</div>
											<div class="col-xs-12 col-sm-6">
												<div>
													<label for="buildingArea">Diện tích sàn</label> <input
														type="text" id="buildingArea" name="buildingArea"
														class="form-control">
												</div>
											</div>
											<!-- PAGE CONTENT ENDS -->
											<div class="col-xs-12" style="margin-top: 10px;">
												<div class="col-xs-12 col-sm-4">
													<div class="form-group">
														<label for="exampleFormControlSelect1">Quận hiện
															có</label> <select style="width: 50%; heigh: 80%"
															class="form-control" id="district" name="district">
															<option value="" disabled selected>--- Chọn Quận
																---</option>
															<c:forEach var="item" items="${district}">
																<option value="${item.key}">${item.value}</option>
															</c:forEach>
														</select>
													</div>
												</div>
												<div class="col-xs-12 col-sm-4">
													<div>
														<label for="buildingArea">Phường</label> <input
															type="text" id="ward" name="ward" class="form-control">
													</div>
												</div>
												<div class="col-xs-12 col-sm-4">
													<div>
														<label for="buildingArea">Đường</label> <input type="text"
															id="street" name="street" class="form-control">
													</div>
												</div>
											</div>
											<div class="col-xs-12" style="margin-top: 10px;">
												<div class="col-xs-12 col-sm-4">
													<div>
														<label for="buildingArea">Số tầng hầm</label> <input
															type="text" id="numberOfBasement" name="numberOfBasement"
															class="form-control">
													</div>
												</div>
												<div class="col-xs-12 col-sm-4">
													<div>
														<label for="buildingArea">Hướng</label> <input type="text"
															id="structure" name="structure" class="form-control">
													</div>
												</div>
												<div class="col-xs-12 col-sm-4">
													<div>
														<label for="buildingArea">Phí dịch vụ</label> <input
															type="text" id="servicecost" name="servicecost"
															class="form-control">
													</div>
												</div>
											</div>
											<div class="col-xs-12" style="margin-top: 10px;">
												<!-- PAGE CONTENT BEGINS -->
												<div class="col-sm-3">
													<div>
														<label for="name">Diện tích từ</label> <input type="text"
															id="areaRentTo" name="areaRentTo" class="form-control">
													</div>
												</div>
												<div class="col-sm-3">
													<div>
														<label for="buildingArea">Diện tích đến</label> <input
															type="text" id="areaRentFrom" name="areaRentFrom"
															class="form-control">
													</div>
												</div>
												<div class="col-sm-3">
													<div>
														<label for="name">Giá thuê từ</label> <input type="text"
															id="costRentTo" name="costRentTo" class="form-control">
													</div>
												</div>
												<div class="col-sm-3">
													<div>
														<label for="buildingArea">Giá thuê đến</label> <input
															type="text" id="costRentFrom" name="costRentFrom"
															class="form-control">
													</div>
												</div>
												<!-- PAGE CONTENT ENDS -->
											</div>
											<div class="col-xs-12" style="margin-top: 10px;">
												<div class="col-xs-12 col-sm-4">
													<div>
														<label for="buildingArea">Tên quản lý</label> <input
															type="text" id="managerName" name="managerName"
															class="form-control">
													</div>
												</div>
												<div class="col-xs-12 col-sm-4">
													<div>
														<label for="buildingArea">Điện thoại quản lý</label> <input
															type="text" id="managerPhone" name="managerPhone"
															class="form-control">
													</div>
												</div>
											</div>
											<div class="col-xs-12" style="margin: 20px 0;">
												<div class="form-group">
													<div class="col-sm-9">
														<c:forEach var="item" items="${buildingTypesModal}">
															<label class='checkbox-inline'> <input
																type="checkbox" value="${item.key}" id="buildingTypes"
																name="buildingTypes"> ${item.value}
															</label>
														</c:forEach>
														<input type="hidden" name="action" value="LIST">
													</div>
												</div>
											</div>
											<div class="col-xs-12">
												<span class="input-group-btn">
													<button type="button" id="searchBuilding"
														style="margin: 10px;" class="btn btn-info btn-sm">
														<span
															class="ace-icon fa fa-search icon-on-right bigger-110"></span>
														Search
													</button>
												</span>
											</div>
										</div>
										<input type="hidden" value="" id="limit" name="limit" /> <input
											type="hidden" value="" id="page" name="page" /> <input
											type="hidden" value="" id="totalPage" name="totalPage" /> <input
											type="hidden" value="" id="mode" name="mode" />
									</form>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!-- /.row -->
				<div class="row">
					<div class="col-xs-12">
						<div class="pull-right">
							<a href='<c:url value="/building-list?action=EDIT"/>'
								class="btn btn-white btn-info btn-bold"><i
								class="fa fa-plus-circle"></i></a>
							<button class="btn btn-white btn-warning btn-bold"
								data-toggle="tooltip" title="Xóa tòa nhà">
								<i class="fa fa-trash"></i>
							</button>
						</div>
					</div>
				</div>
				<!-- /. row -->
				<br />
				<div class="row">
					<div class="col-xs-12">
						<table id="simple-table"
							class="table table-striped table-bordered table-hover">
							<thead>
								<tr>
									<th>Tên sản phẩm</th>
									<th>Địa chỉ</th>
									<th>Tên quản lí</th>
									<th>Số điện thoại</th>
									<th>Diện tích sàn</th>
									<th>Giá thuê</th>
									<th>Loại tòa nhà</th>
									<th>Mô tả</th>
									<th>Thao Tác</th>
								</tr>
							</thead>

							<tbody>
								<c:forEach var="item" items="${buildingModels}">
									<tr>
										<td>${item.name}</td>
										<td>${item.address}</td>
										<td>${item.managerName}</td>
										<td>${item.managerPhone}</td>
										<td>${item.buildingArea}</td>
										<td>${item.costRent}</td>
										<td>${item.buildingTypesDiscription}</td>
										<td>${item.costDescription}</td>
										<td>
											<button class="btn btn-xs" data-toggle="tooltip"
												title="Xóa tòa nhà" onclick="assigmentBuilding()">
												<i class="menu-icon fa fa-pencil-square-o"></i>
											</button>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						<ul class="pagination" id="pagination"></ul>
					</div>
				</div>
				<!-- /.row -->
			</div>
			<!-- /.page-content -->
		</div>
	</div>
	<!-- /.main-content -->
	<div id="buildingModal" class="modal fade" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Danh sách nhân</h4>
				</div>
				<div class="modal-body">
					<table class="table table-bordered">
						<thead>
							<tr>
								<td>Chọn nhân viên</td>
								<td>Tên nhân viên</td>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td><input type="checkbox" value="2"></td>
								<td>Ngọc Huy</td>
							</tr>
							<tr>
								<td><input type="checkbox" value="2"></td>
								<td>Quang Hải</td>
							</tr>
							<tr>
								<td><input type="checkbox" checked value="2"></td>
								<td>Văn Cảnh</td>
							</tr>
							<tr>
								<td><input type="checkbox" value="2"></td>
								<td>Tùng</td>
							</tr>
						</tbody>
					</table>
				</div>
				<div class="modal-footer"></div>
			</div>
		</div>
	</div>
	<script>
		var totalPages = ${pageModel.totalPage};
		var currentPage = ${pageModel.page};
		var limit = ${pageModel.limit};
		$(function() {
			window.pagObj = $('#pagination').twbsPagination({
				totalPages : totalPages,
				visiblePages : 3,
				startPage : currentPage,
				onPageClick : function(event, page) {
					if (currentPage != page) {
						$('#totalPage').val(totalPages);
						$('#limit').val(limit);
						$('#page').val(page);
						$('#mode').val('PAGING');
						$('#buildingForm').submit();
					}
				}
			});
		});
	</script>
</body>

</html>