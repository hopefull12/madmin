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
											<th width="25%" class="text-center" colspan="2">Prayer Time Files</th>											
										</tr>
									</thead>
									<tbody>
										<c:forEach var="file" items="${uploadedfiles}">		
											<c:if test="${file.category == 'PrayerTimes' }">								
												<tr>											
													<td><a target="_blank" href="displayFiles/${file.name}/">${file.displayName}</a></td>	
													<td><a href="deleteDocument/${file.id}/">Delete</a></td>										
												</tr>		
											</c:if>																				
										</c:forEach>
									</tbody>
								</table>
								
								<table class="display table table-bordered table-striped" id="datatable-aja">
									<thead>
										<tr>											
											<th width="25%" class="text-center" colspan="2">Ramadan Files</th>											
										</tr>
									</thead>
									<tbody>
										<c:forEach var="file" items="${uploadedfiles}">	
											<c:if test="${file.category == 'Ramadan' }">									
												<tr>											
													<td><a target="_blank" href="displayFiles/${file.name}/">${file.displayName}</a></td>	
													<td><a href="deleteDocument/${file.id}/">Delete</a></td>										
												</tr>	
											</c:if>																					
										</c:forEach>
									</tbody>
								</table>	
								
								<table class="display table table-bordered table-striped" id="datatable-aja">
									<thead>
										<tr>											
											<th width="25%" class="text-center" colspan="2">Misc Files</th>											
										</tr>
									</thead>
									<tbody>
										<c:forEach var="file" items="${uploadedfiles}">	
											<c:if test="${file.category == 'Misc' }">									
												<tr>											
												<td><a target="_blank" href="displayFiles/${file.name}/">${file.displayName}</a></td>	
												<td><a href="deleteDocument/${file.id}/">Delete</a></td>										
												</tr>		
											</c:if>																				
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