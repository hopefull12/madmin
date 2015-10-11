<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

				<section role="main" class="content-body">
					<header class="page-header">
						<h2>Create News Item</h2>

					</header>

					<!-- start: page -->
					<div class="row">

						<div class="col-md-12">
							
							<form:form id="form2" action="${pageContext.request.contextPath}/item/news/create" cssClass="form-horizontal form-bordered" method="POST" commandName="itemBean"  enctype="multipart/form-data">
								<section class="panel">
									<header class="panel-heading">
										<div class="panel-actions">
											<a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
											<a href="#" class="panel-action panel-action-dismiss" data-panel-dismiss></a>
										</div>

										<h2 class="panel-title">Create New Item</h2>

									</header>
									<div class="panel-body">
									
										<div class="form-group">
												<label class="col-sm-4 control-label" for="inputSuccess">News Text</label>
												<div class="col-sm-8">												
													<form:textarea rows="4" cols="50" path="longDescription" />
												</div>
										</div>																																		
										
										<div class="form-group">
											<label class="col-sm-4 control-label">News Display Dates</label>
											<div class="col-sm-8">
												<div class="input-daterange input-group" data-plugin-datepicker>
													<span class="input-group-addon">
														<i class="fa fa-calendar"></i>
													</span>
													<form:input path="validFrom" cssClass="form-control"/>
													
													<span class="input-group-addon">to</span>
													<form:input path="validTo" cssClass="form-control"/>
													
												</div>
											</div>
										</div>																												
										
																																																
										
									</div>
									<footer class="panel-footer">
										<button class="btn btn-primary">Submit</button>
										<button type="reset" class="btn btn-default">Reset</button>
									</footer>
								</section>
							</form:form>
						</div>
					</div>
					



					<!-- end: page -->
				</section>