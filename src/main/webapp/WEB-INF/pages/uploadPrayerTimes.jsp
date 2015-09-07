				<section role="main" class="content-body">
					<header class="page-header">
						<h2>Form Layouts</h2>
					
						<div class="right-wrapper pull-right">
							<ol class="breadcrumbs">
								<li>
									<a href="index.html">
										<i class="fa fa-home"></i>
									</a>
								</li>
								<li><span>Forms</span></li>
								<li><span>Layouts</span></li>
							</ol>
					
							<a class="sidebar-right-toggle" data-open="sidebar-right"><i class="fa fa-chevron-left"></i></a>
						</div>
					</header>

					<!-- start: page -->
					<div class="row">

						<div class="col-md-12">
							
							<form:form id="form2" action="createPTCSVFile" cssClass="form-horizontal form-bordered" method="POST" commandName="prayerTimes"  enctype="multipart/form-data">
								<section class="panel">
									<header class="panel-heading">
										<div class="panel-actions">
											<a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
											<a href="#" class="panel-action panel-action-dismiss" data-panel-dismiss></a>
										</div>

										<h2 class="panel-title">Bordered Form</h2>

										<p class="panel-subtitle">
											Use <code>.form-bordered</code> class to style horizontal fields with borders.
										</p>
									</header>
									<div class="panel-body">
									
										<div class="form-group">
												<label class="col-sm-4 control-label" for="inputSuccess">Select Month</label>
												<div class="col-sm-8">												
													<form:select path="month" items="${months}"/>
												</div>
										</div>										
										
										
										<div class="form-group">
											<label class="col-sm-4 control-label">File Upload</label>
											<div class="col-sm-8">
												<div class="fileupload fileupload-new" data-provides="fileupload">
													<div class="input-append">
														<div class="uneditable-input">
															<i class="fa fa-file fileupload-exists"></i>
															<span class="fileupload-preview"></span>
														</div>
														<span class="btn btn-default btn-file">
															<span class="fileupload-exists">Change</span>
															<span class="fileupload-new">Select file</span>
															<input type="file" name="prayerTimesFile" class="form-control">															
														</span>
														<a href="#" class="btn btn-default fileupload-exists" data-dismiss="fileupload">Remove</a>
													</div>
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