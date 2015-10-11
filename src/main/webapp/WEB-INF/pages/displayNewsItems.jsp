<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

				<section role="main" class="content-body">
					<header class="page-header">
						<h2>Uploaded Documents</h2>
					
						
					</header>	

					<!-- start: page -->
					<div class="row">
						<div class="col-md-4">
						</div>
						<div class="col-md-4">
							
							<div class="panel-body">
								<table class="display table table-bordered table-striped" id="datatable-aja">
									<thead>
										<tr>											
											<th width="25%" class="text-center" colspan="5">All News Items</th>											
										</tr>
										<tr>											
											<th width="25%" class="text-center">News Text</th>
											<th width="25%" class="text-center">Valid From</th>
											<th width="25%" class="text-center">Valid To</th>
											<th width="25%" class="text-center">Date Created</th>
											<th width="25%" class="text-center">Actions</th>																						
										</tr>										
									</thead>
									<tbody>
										<c:forEach var="item" items="${newsItems}">		
																		
											<tr>								
														
												<td>${item.longDescription}</td>
												<td>${item.validFrom}</td>
												<td>${item.validTo}</td>
												<td>${item.dateCreated}</td>
												<td><a href="${pageContext.request.contextPath}/item/news/delete/${item.id}/">Delete</a></td>		
																						
											</tr>		
																														
										</c:forEach>
									</tbody>
								</table>
								
							
																	
								
								<script type="text/javascript">
								
								$(document).ready(function() {
								    $('#datatable-aja').DataTable();
								} );								
								
								</script>								
								
							</div>
						</div>
						<div class="col-md-4">
						</div>						
					</div>


					<!-- end: page -->
				</section>