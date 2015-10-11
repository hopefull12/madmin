<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

				<section role="main" class="content-body">
					<header class="page-header">
						<h2>Form Layouts</h2>
					
						
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
											<th width="12%">Date</th>
											<th width="8%">Fajar</th>
											<th width="8%">Fajar Iqama</th>
											<th width="8%">Sunrise</th>											
											<th width="8%">Duhur</th>
											<th width="8%">Duhur Iqama</th>
											<th width="8%">Asr</th>
											<th width="8%">Asr Iqama</th>
											<th width="8%">Maghrib</th>
											<th width="8%">Maghrib Iqama</th>
											<th width="8%">Isha</th>
											<th width="8%">Isha Iqama</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="dailyprayertime" items="${prayertimes}">										
											<tr>
											<td>${dailyprayertime.date}</td>
											<td>${dailyprayertime.fajarBeginTime}</td>
											<td>${dailyprayertime.fajarIqamaTime}</td>
											<td>${dailyprayertime.sunriseTime}</td>
											<td>${dailyprayertime.dhuhrTIme}</td>
											<td>${dailyprayertime.dhuhrIqamaTIme}</td>
											<td>${dailyprayertime.asrTime}</td>
											<td>${dailyprayertime.asrIqamaTIme}</td>
											<td>${dailyprayertime.maghribTIme}</td>
											<td>${dailyprayertime.maghribIqamaTIme}</td>
											<td>${dailyprayertime.ishaTIme}</td>
											<td>${dailyprayertime.ishaIqamaTIme}</td>
											</tr>																						
										</c:forEach>
									</tbody>
								</table>
								
								<script type="text/javascript">
								
								$(document).ready(function() {
								    $('#datatable-aja').DataTable({
								    	"iDisplayLength": 31	
								    });
								} );								
								
								</script>									
								
							</div>
						</div>
						<div class="col-md-4">
						</div>						
					</div>


					<!-- end: page -->
				</section>