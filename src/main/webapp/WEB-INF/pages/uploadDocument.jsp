<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

				<section role="main" class="content-body">
					<header class="page-header">
						<h2>Upload Documents</h2>

					</header>

					<!-- start: page -->
					<div class="row">

						<div class="col-md-12">
							
							<form:form id="form2" action="createFile" cssClass="form-horizontal form-bordered" method="POST" commandName="docBean"  enctype="multipart/form-data">
								<section class="panel">
									<header class="panel-heading">
										<div class="panel-actions">
											<a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
											<a href="#" class="panel-action panel-action-dismiss" data-panel-dismiss></a>
										</div>

										<h2 class="panel-title">Upload Documents</h2>

									</header>
									<div class="panel-body">
									
										<div class="form-group">
												<label class="col-sm-4 control-label" for="inputSuccess">Display File Name</label>
												<div class="col-sm-8">												
													<form:input path="displayFileName" />
												</div>
										</div>									
									
										<div class="form-group">
												<label class="col-sm-4 control-label" for="inputSuccess">Select Category</label>
												<div class="col-sm-8">												
													<form:select id="fileCategory" path="category">
														<form:option value="">&nbsp;</form:option>
														<form:options items="${categories}" />													
													</form:select>
												</div>
										</div>				
										
										<div class="form-group pt-field">
												<label class="col-sm-4 control-label" for="inputSuccess">File Type</label>
												<div class="col-sm-8">												
													<form:select path="fileType">
														<form:option value="">&nbsp;</form:option>
														<form:options items="${fileTypes}" />
													</form:select>
												</div>
										</div>
										
										<div class="form-group pt-field">
												<label class="col-sm-4 control-label" for="inputSuccess">Prayer times for which Month</label>
												<div class="col-sm-8">												
													<form:select path="month">
														<form:option value="">&nbsp;</form:option>
														<form:options items="${months}" />													
													</form:select>
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
															<input type="file" name="file" class="form-control">															
														</span>
														<a href="#" class="btn btn-default fileupload-exists" data-dismiss="fileupload">Remove</a>
													</div>
												</div>
											</div>
										</div>		
										
										<div class="form-group">
											<label class="col-sm-4 control-label">File Valid Dates</label>
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