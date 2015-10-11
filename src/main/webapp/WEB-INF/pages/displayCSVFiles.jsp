<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

				<section role="main" class="content-body">
					<header class="page-header">
						<h2>Prayer Times CSV Files</h2>
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
											<th width="25%" class="text-center">Uploaded Files</th>											
										</tr>
									</thead>
									<tbody>
										<c:forEach var="fileName" items="${prayerfiles}">										
											<tr>											
											<td><a href="displayPTCSVFiles/${fileName}/">${fileName}</a></td>											
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