<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"
	prefix="dec"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Building Edit</title>
</head>
<body>
	<div class="main-content" id="building-edit">
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
					<div class="col-xs-12">
						<div class="form-horizontal" role="form" id="formEdit">
							<form id="buildingEdit">
								<div class="form-group edit-form">
									<label class="col-sm-3 control-label no-padding-right"
										for="form-field-1"> Tên tòa nhà </label>

									<div class="col-sm-9">
										<input type="text" id="name" class="form-control" name='name'>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right"
										for="form-field-1"> Số tầng hầm </label>

									<div class="col-sm-9">
										<input type="text" id="numberOfBasement" class="form-control"
											name='numberOfBasement'>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right"
										for="form-field-1"> Phí dịch vụ </label>

									<div class="col-sm-9">
										<input type="text" id="form-field-1" class="form-control"
											id="serviceCost" name="serviceCost">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right"
										for="form-field-1"> Diện tích thuê </label>

									<div class="col-sm-9">
										<input type="text" id="buildingArea" class="form-control"
											name="buildingArea">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right"
										for="form-field-1"> Giá thuê </label>

									<div class="col-sm-9">
										<input type="text" id="costRent" class="form-control"
											name="costRent">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right"
										for="form-field-1"> Mô tả </label>

									<div class="col-sm-9">
										<input type="text" id="costDescription" class="form-control"
											name="costDescription">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right"
										for="form-field-1"> Hướng </label>

									<div class="col-sm-9">
										<input type="text" id="structure" class="form-control"
											name='structure'>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right"
										for="form-field-1"> Phường </label>

									<div class="col-sm-9">
										<input type="text" id="ward" class="form-control"
											name="ward">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right"
										for="form-field-1"> Đường </label>

									<div class="col-sm-9">
										<input type="text" id="street" class="form-control"
											name="street">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right"
										for="form-field-1"> Số điện thoại quản lý </label>

									<div class="col-sm-9">
										<input type="text" id="form-field-1" class="form-control"
											name="managerPhone">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right"
										for="form-field-1"> Tên quản lý </label>

									<div class="col-sm-9">
										<input type="text" id="form-field-1" class="form-control"
											name="managerName">
									</div>
								</div>
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
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right"
										for="form-field-1"> Loại tòa nhà </label>

									<div class="col-sm-9">
										<c:forEach var="item" items="${buildingTypesModal}">
											<label class='checkbox-inline'> <input
												type="checkbox" value="${item.key}" id="buildingTypes"
												name="buildingTypes"> ${item.value}
											</label>
										</c:forEach>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right"
										for="form-field-1"> </label>

									<div class="col-sm-9">
										<button type="button" class="btn btn-primary" id="addBuilding">Thêm
											tòa nhà</button>
										<button type="button" class="btn btn-primary">Hủy</button>
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
				<!-- /.row -->
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
								</tr>
							</thead>

							<tbody>
									<tr>
										<td>${building.name}</td>
										<td>${building.address}</td>
										<td>${building.managerName}</td>
										<td>${building.managerPhone}</td>
										<td>${building.buildingArea}</td>
										<td>${building.costRent}</td>
										<td>${building.buildingTypesDiscription}</td>
										<td>${building.costDescription}</td>
									</tr>
							</tbody>
						</table>
					</div>
				</div>
				<!-- /.row -->
			</div>
			<!-- /.page-content -->
		</div>
	</div>
	<!-- /.main-content -->
</body>
</html>