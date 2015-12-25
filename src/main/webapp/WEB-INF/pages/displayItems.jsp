<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<section role="main" class="content-body">
    <header class="page-header">
        <h2></h2>

    </header>

    <c:choose>

    <c:when test="${itemBean.itemType == 'MEDIA_AUDIO' || itemBean.itemType == 'MEDIA_VIDEO' || itemBean.itemType == 'MEDIA_IMAGE'}" >
        <!-- Display all document items start -->
        <div class="row">
            <div class="col-md-2">
            </div>
            <div class="col-md-8">
                <section class="panel">
                    <header class="panel-heading">
                        <div class="panel-actions">
                            <a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
                            <a href="#" class="panel-action panel-action-dismiss" data-panel-dismiss></a>
                        </div>
                        <h2 class="panel-title text-center">Manage ${itemBean.itemType}</h2>
                    </header>
                    <div class="panel-body">
                        <table class="display table table-bordered table-striped" id="datatable-media">
                            <thead>
                            <tr>
                                <th width="60%" class="text-center">Media Name</th>
                                <th width="10%" class="text-center">Valid From</th>
                                <th width="10%" class="text-center">Valid To</th>
                                <th width="10%" class="text-center">Date Created</th>
                                <th width="10%" class="text-center">Actions</th>
                            </tr>
                            </thead>
                            <tbody>

                            <c:forEach var="itemVar" items="${items}">

                                <tr>

                                    <td><a href="${pageContext.request.contextPath}/displayPTCSVFiles/${itemVar.displayName}/">${itemVar.displayName}</a></td>
                                    <td><fmt:formatDate value="${itemVar.validFrom}" pattern="MMM-dd-yyyy"/></td>
                                    <td><fmt:formatDate value="${itemVar.validTo}" pattern="MMM-dd-yyyy"/></td>
                                    <td><fmt:formatDate value="${itemVar.dateCreated}" pattern="MMM-dd-yyyy"/></td>
                                    <td><a href="${pageContext.request.contextPath}/item/delete?id=${itemVar.id}&itemType=${itemVar.itemType}">Delete</a></td>

                                </tr>

                            </c:forEach>

                            </tbody>
                        </table>

                        <script type="text/javascript">

                            $(document).ready(function() {
                                $('#datatable-media').DataTable();
                            } );

                        </script>

                    </div>
                </section>
            </div>
            <div class="col-md-2">
            </div>
        </div>
        <!-- Display all document items end -->

        <!-- Display form for creating document item -->
        <div class="row">

            <div class="col-md-2"></div>
            <div class="col-md-8">

                <form:form id="form2" action="${pageContext.request.contextPath}/item/create" cssClass="form-horizontal form-bordered" method="POST" commandName="itemBean"  enctype="multipart/form-data">
                    <section class="panel">
                        <header class="panel-heading">
                            <div class="panel-actions">
                                <a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
                                <a href="#" class="panel-action panel-action-dismiss" data-panel-dismiss></a>
                            </div>
                            <h2 class="panel-title text-center">Create Media</h2>
                        </header>
                        <div class="panel-body">

                            <div class="form-group">
                                <label class="col-sm-4 control-label" for="inputSuccess">Title</label>
                                <div class="col-sm-8">
                                    <form:input path="displayName" />
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-4 control-label" for="inputSuccess">Media Link</label>
                                <div class="col-sm-8">
                                    <form:input path="name" />
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-4 control-label">Media Display Dates</label>
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
                            
                            <div class="form-group">
                                <label class="col-sm-4 control-label" for="inputSuccess">Speaker Name (optional)</label>
                                <div class="col-sm-8">
                                    <form:input path="longDescription" />
                                </div>
                            </div>                            
                            
                            <div class="form-group">
                                <label class="col-sm-4 control-label" for="inputSuccess">Description</label>
                                <div class="col-sm-8">
                                    <form:textarea rows="4" cols="50" path="shortDescription" />
                                </div>
                            </div>                            

                        </div>
                        <form:hidden path="itemType"/>
                        <footer class="panel-footer text-center">
                            <button class="btn btn-primary">Submit</button>
                            <button type="reset" class="btn btn-default">Reset</button>
                        </footer>
                    </section>
                </form:form>
            </div>
            <div class="col-md-2"></div>
        </div>
        <!-- Display form for creating document item End -->

    </c:when>

    <c:when test="${itemBean.itemType == 'PT_CSV'}" >
            <!-- Display all document items start -->
            <div class="row">
                <div class="col-md-2">
                </div>
                <div class="col-md-8">
                	<section class="panel">
	                    <header class="panel-heading">
	                        <div class="panel-actions">
	                            <a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
	                            <a href="#" class="panel-action panel-action-dismiss" data-panel-dismiss></a>
	                        </div>
	                        <h2 class="panel-title text-center">Prayer Time CSV Files</h2>
	                    </header>
	                    <div class="panel-body">
	                        <table class="display table table-bordered table-striped" id="datatable-document">
	                            <thead>	                      
	                            <tr>
	                                <th width="60%" class="text-center">File Name</th>
	                                <th width="10%" class="text-center">Valid From</th>
	                                <th width="10%" class="text-center">Valid To</th>
	                                <th width="10%" class="text-center">Date Created</th>
	                                <th width="10%" class="text-center">Actions</th>
	                            </tr>
	                            </thead>
	                            <tbody>
	
	                            <c:forEach var="itemVar" items="${items}">
	
	                                <tr>
	
	                                    <td><a href="${pageContext.request.contextPath}/displayPTCSVFiles/${itemVar.attachment1FileName}/">${itemVar.attachment1Name}</a></td>
	                                    <td><fmt:formatDate value="${itemVar.validFrom}" pattern="MMM-dd-yyyy"/></td>
	                                    <td><fmt:formatDate value="${itemVar.validTo}" pattern="MMM-dd-yyyy"/></td>
	                                    <td><fmt:formatDate value="${itemVar.dateCreated}" pattern="MMM-dd-yyyy"/></td>
	                                    <td><a href="${pageContext.request.contextPath}/item/delete?id=${itemVar.id}&itemType=${itemVar.itemType}">Delete</a></td>
	
	                                </tr>
	
	                            </c:forEach>
	
	                            </tbody>
	                        </table>
	
	                        <script type="text/javascript">
	
	                            $(document).ready(function() {
	                                $('#datatable-document').DataTable();
	                            } );
	
	                        </script>
	
	                    </div>
                   	</section>
                </div>
                <div class="col-md-2">
                </div>
            </div>
            <!-- Display all document items end -->

            <!-- Display form for creating document item -->
            <div class="row">

				<div class="col-md-2"></div>
                <div class="col-md-8">

                    <form:form id="form2" action="${pageContext.request.contextPath}/item/create" cssClass="form-horizontal form-bordered" method="POST" commandName="itemBean"  enctype="multipart/form-data">
                        <section class="panel">
                            <header class="panel-heading">
                                <div class="panel-actions">
                                    <a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
                                    <a href="#" class="panel-action panel-action-dismiss" data-panel-dismiss></a>
                                </div>
                                <h2 class="panel-title text-center">Upload New Prayer Times CSV File</h2>
                            </header>
                            <div class="panel-body">

                                <div class="form-group">
                                    <label class="col-sm-4 control-label" for="inputSuccess">Display File Name</label>
                                    <div class="col-sm-8">
                                        <form:input path="displayName" />
                                    </div>
                                </div>

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
															<input type="file" name="attachments[0]" class="form-control">
														</span>
                                                <a href="#" class="btn btn-default fileupload-exists" data-dismiss="fileupload">Remove</a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <form:hidden path="itemType"/>

                            </div>
                            <footer class="panel-footer text-center">
                                <button class="btn btn-primary">Submit</button>
                                <button type="reset" class="btn btn-default">Reset</button>
                            </footer>
                        </section>
                    </form:form>
                </div>
                <div class="col-md-2"></div>
            </div>
            <!-- Display form for creating document item End -->

        </c:when>    
        <c:when test="${itemBean.itemType == 'EVENT'}" >
            <!-- Display all event items start -->
            <div class="row">
                <div class="col-md-2">
                </div>
                <div class="col-md-8">
               	<section class="panel">
                    <header class="panel-heading">
                        <div class="panel-actions">
                            <a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
                            <a href="#" class="panel-action panel-action-dismiss" data-panel-dismiss></a>
                        </div>
                        <h2 class="panel-title text-center">Event List</h2>
                    </header>
                    <div class="panel-body">
                        <table class="display table table-bordered table-striped" id="datatable-event">
                            <thead>                         
                            <tr>
                                <th width="60%" class="text-center">Event Name</th>
                                <th width="10%" class="text-center">Display From</th>
                                <th width="10%" class="text-center">Display To</th>
                                <th width="10%" class="text-center">Date Created</th>
                                <th width="10%" class="text-center">Actions</th>
                            </tr>
                            </thead>
                            <tbody>

                            <c:forEach var="itemVar" items="${items}">

                                <tr>

                                    <td>${itemVar.displayName}</td>
                                    <td><fmt:formatDate value="${itemVar.validFrom}" pattern="MMM-dd-yyyy"/></td>
                                    <td><fmt:formatDate value="${itemVar.validTo}" pattern="MMM-dd-yyyy"/></td>
                                    <td><fmt:formatDate value="${itemVar.dateCreated}" pattern="MMM-dd-yyyy"/></td>
                                    <td><a href="${pageContext.request.contextPath}/item/delete?id=${itemVar.id}&itemType=${itemVar.itemType}">Delete</a></td>

                                </tr>

                            </c:forEach>

                            </tbody>
                        </table>

                        <script type="text/javascript">

                            $(document).ready(function() {
                                $('#datatable-event').DataTable();
                            } );

                        </script>

                    </div>
                   </section>
                </div>
                <div class="col-md-2">
                </div>
            </div>
            <!-- Display all event items end -->

            <!-- Display form for creating event item -->
            <div class="row">

                <div class="col-md-2">
                </div>
                <div class="col-md-8">

                    <form:form id="form2" action="${pageContext.request.contextPath}/item/create" cssClass="form-horizontal form-bordered" method="POST" commandName="itemBean"  enctype="multipart/form-data">
                        <section class="panel">
                            <header class="panel-heading">
                                <div class="panel-actions">
                                    <a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
                                    <a href="#" class="panel-action panel-action-dismiss" data-panel-dismiss></a>
                                </div>
                                <h2 class="panel-title text-center">Create New Event</h2>
                            </header>
                            <div class="panel-body">

                                <div class="form-group">
                                    <label class="col-sm-4 control-label" for="inputSuccess">Event Name</label>
                                    <div class="col-sm-8">
                                        <form:input path="displayName" />
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-sm-4 control-label">Event Display Dates</label>
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
															<input type="file" name="attachments[0]" class="form-control">
														</span>
                                                <a href="#" class="btn btn-default fileupload-exists" data-dismiss="fileupload">Remove</a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <form:hidden path="itemType"/>

                            </div>
                            <footer class="panel-footer text-center">
                                <button class="btn btn-primary">Submit</button>
                                <button type="reset" class="btn btn-default">Reset</button>
                            </footer>
                        </section>
                    </form:form>
                </div>
                <div class="col-md-2">
                </div>                
            </div>
            <!-- Display form for creating event item End -->

        </c:when>        
        
        <c:when test="${itemBean.itemType == 'DOWNLOADDOC'}" >
            <!-- Display all document items start -->
            <div class="row">
                <div class="col-md-2">
                </div>
                <div class="col-md-8">
               	<section class="panel">
                    <header class="panel-heading">
                        <div class="panel-actions">
                            <a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
                            <a href="#" class="panel-action panel-action-dismiss" data-panel-dismiss></a>
                        </div>
                        <h2 class="panel-title text-center">Document List</h2>
                    </header>
                    <div class="panel-body">
                        <table class="display table table-bordered table-striped" id="datatable-document">
                            <thead>
                            <tr>
                                <th width="60%" class="text-center">File Name</th>
                                <th width="10%" class="text-center">Valid From</th>
                                <th width="10%" class="text-center">Valid To</th>
                                <th width="10%" class="text-center">Date Created</th>
                                <th width="10%" class="text-center">Actions</th>
                            </tr>
                            </thead>
                            <tbody>

                            <c:forEach var="itemVar" items="${items}">

                                <tr>

                                    <td>${itemVar.attachment1Name}</td>
                                    <td><fmt:formatDate value="${itemVar.validFrom}" pattern="MMM-dd-yyyy"/></td>
                                    <td><fmt:formatDate value="${itemVar.validTo}" pattern="MMM-dd-yyyy"/></td>
                                    <td><fmt:formatDate value="${itemVar.dateCreated}" pattern="MMM-dd-yyyy"/></td>
                                    <td><a href="${pageContext.request.contextPath}/item/delete?id=${itemVar.id}&itemType=${itemVar.itemType}">Delete</a></td>

                                </tr>

                            </c:forEach>

                            </tbody>
                        </table>

                        <script type="text/javascript">

                            $(document).ready(function() {
                                $('#datatable-document').DataTable();
                            } );

                        </script>

                    </div>
                  </section>
                </div>
                <div class="col-md-2">
                </div>
            </div>
            <!-- Display all document items end -->

            <!-- Display form for creating document item -->
            <div class="row">

				<div class="col-md-2"></div>
                <div class="col-md-8">

                    <form:form id="form2" action="${pageContext.request.contextPath}/item/create" cssClass="form-horizontal form-bordered" method="POST" commandName="itemBean"  enctype="multipart/form-data">
                        <section class="panel">
                            <header class="panel-heading">
                                <div class="panel-actions">
                                    <a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
                                    <a href="#" class="panel-action panel-action-dismiss" data-panel-dismiss></a>
                                </div>
                                <h2 class="panel-title text-center">Upload Documents</h2>
                            </header>
                            <div class="panel-body">

                                <div class="form-group">
                                    <label class="col-sm-4 control-label" for="inputSuccess">Display File Name</label>
                                    <div class="col-sm-8">
                                        <form:input path="displayName" />
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-sm-4 control-label" for="inputSuccess">Display Page</label>
                                    <div class="col-sm-8">
                                        <form:select id="fileCategory" path="category">                                            
                                            <form:options items="${categories}" />
                                        </form:select>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-sm-4 control-label">Document Display Dates</label>
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
															<input type="file" name="attachments[0]" class="form-control">
														</span>
                                                <a href="#" class="btn btn-default fileupload-exists" data-dismiss="fileupload">Remove</a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <form:hidden path="itemType"/>

                            </div>
                            <footer class="panel-footer text-center">
                                <button class="btn btn-primary">Submit</button>
                                <button type="reset" class="btn btn-default">Reset</button>
                            </footer>
                        </section>
                    </form:form>
                </div>
                <div class="col-md-2"></div>
            </div>
            <!-- Display form for creating document item End -->

        </c:when>
        
        <c:when test="${itemBean.itemType == 'PT_PDF'}" >
            <!-- Display all document items start -->
            <div class="row">
                <div class="col-md-2">
                </div>
                <div class="col-md-8">
				<section class="panel">
				    <header class="panel-heading">
				        <div class="panel-actions">
				            <a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
				            <a href="#" class="panel-action panel-action-dismiss" data-panel-dismiss></a>
				        </div>
				        <h2 class="panel-title text-center">Prayer Time PDF Files List</h2>
				    </header>
                    <div class="panel-body">
                        <table class="display table table-bordered table-striped" id="datatable-document">
                            <thead>
                            <tr>
                                <th width="60%" class="text-center">File Name</th>
                                <th width="10%" class="text-center">Valid From</th>
                                <th width="10%" class="text-center">Valid To</th>
                                <th width="10%" class="text-center">Date Created</th>
                                <th width="10%" class="text-center">Actions</th>
                            </tr>
                            </thead>
                            <tbody>

                            <c:forEach var="itemVar" items="${items}">

                                <tr>

                                    <td>${itemVar.attachment1Name}</td>
                                    <td><fmt:formatDate value="${itemVar.validFrom}" pattern="MMM-dd-yyyy"/></td>
                                    <td><fmt:formatDate value="${itemVar.validTo}" pattern="MMM-dd-yyyy"/></td>
                                    <td><fmt:formatDate value="${itemVar.dateCreated}" pattern="MMM-dd-yyyy"/></td>
                                    <td><a href="${pageContext.request.contextPath}/item/delete?id=${itemVar.id}&itemType=${itemVar.itemType}">Delete</a></td>

                                </tr>

                            </c:forEach>

                            </tbody>
                        </table>

                        <script type="text/javascript">

                            $(document).ready(function() {
                                $('#datatable-document').DataTable();
                            } );

                        </script>

                    </div>
                    </section>
                </div>
                <div class="col-md-2">
                </div>
            </div>
            <!-- Display all document items end -->

            <!-- Display form for creating document item -->
            <div class="row">

				<div class="col-md-2"></div>
                <div class="col-md-8">

                    <form:form id="form2" action="${pageContext.request.contextPath}/item/create" cssClass="form-horizontal form-bordered" method="POST" commandName="itemBean"  enctype="multipart/form-data">
                        <section class="panel">
                            <header class="panel-heading">
                                <div class="panel-actions">
                                    <a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
                                    <a href="#" class="panel-action panel-action-dismiss" data-panel-dismiss></a>
                                </div>
                                <h2 class="panel-title text-center">Upload Prayer Time PDF File</h2>
                            </header>
                            <div class="panel-body">

                                <div class="form-group">
                                    <label class="col-sm-4 control-label" for="inputSuccess">Display File Name</label>
                                    <div class="col-sm-8">
                                        <form:input path="displayName" />
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-sm-4 control-label" for="inputSuccess">Display Page</label>
                                    <div class="col-sm-8">
                                        <form:select id="fileCategory" path="category">                                            
                                            <form:options items="${categories}" />
                                        </form:select>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-sm-4 control-label">Document Display Dates</label>
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
															<input type="file" name="attachments[0]" class="form-control">
														</span>
                                                <a href="#" class="btn btn-default fileupload-exists" data-dismiss="fileupload">Remove</a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <form:hidden path="itemType"/>

                            </div>
                            <footer class="panel-footer text-center">
                                <button class="btn btn-primary">Submit</button>
                                <button type="reset" class="btn btn-default">Reset</button>
                            </footer>
                        </section>
                    </form:form>
                </div>
                <div class="col-md-2"></div>
            </div>
            <!-- Display form for creating document item End -->

        </c:when>        
        
        
        <c:when test="${itemBean.itemType == 'ADS'}" >
            <!-- Display all paid ad items start -->
            <div class="row">
                <div class="col-md-2">
                </div>
                <div class="col-md-8">
               	<section class="panel">
                    <header class="panel-heading">
                        <div class="panel-actions">
                            <a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
                            <a href="#" class="panel-action panel-action-dismiss" data-panel-dismiss></a>
                        </div>
                        <h2 class="panel-title text-center">ADs List</h2>
                    </header>
                    <div class="panel-body">
                        <table class="display table table-bordered table-striped" id="datatable-paid-ad">
                            <thead>                         
                            <tr>
                                <th width="60%" class="text-center">Ad Name</th>
                                <th width="10%" class="text-center">Valid From</th>
                                <th width="10%" class="text-center">Valid To</th>
                                <th width="10%" class="text-center">Date Created</th>
                                <th width="10%" class="text-center">Actions</th>
                            </tr>
                            </thead>
                            <tbody>

                            <c:forEach var="itemVar" items="${items}">

                                <tr>

                                    <td>${itemVar.attachment1Name}</td>
                                    <td><fmt:formatDate value="${itemVar.validFrom}" pattern="MMM-dd-yyyy"/></td>
                                    <td><fmt:formatDate value="${itemVar.validTo}" pattern="MMM-dd-yyyy"/></td>
                                    <td><fmt:formatDate value="${itemVar.dateCreated}" pattern="MMM-dd-yyyy"/></td>
                                    <td><a href="${pageContext.request.contextPath}/item/delete?id=${itemVar.id}&itemType=${itemVar.itemType}">Delete</a></td>

                                </tr>

                            </c:forEach>

                            </tbody>
                        </table>

                        <script type="text/javascript">

                            $(document).ready(function() {
                                $('#datatable-paidad').DataTable();
                            } );

                        </script>

                    </div>
                    </section>
                </div>
                <div class="col-md-2">
                </div>
            </div>
            <!-- Display all paid ad items end -->

            <!-- Display form for creating paid ad item -->
            <div class="row">
                <div class="col-md-2">
                </div>
                <div class="col-md-8">

                    <form:form id="form2" action="${pageContext.request.contextPath}/item/create" cssClass="form-horizontal form-bordered" method="POST" commandName="itemBean"  enctype="multipart/form-data">
                        <section class="panel">
                            <header class="panel-heading">
                                <div class="panel-actions">
                                    <a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
                                    <a href="#" class="panel-action panel-action-dismiss" data-panel-dismiss></a>                                    
                                </div>
                                <h2 class="panel-title text-center">Create New Ad</h2>
                            </header>
                            <div class="panel-body">

                                <div class="form-group">
                                    <label class="col-sm-4 control-label" for="inputSuccess">Ad Name</label>
                                    <div class="col-sm-8">
                                        <form:input path="displayName" />
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-sm-4 control-label">Ad Display Dates</label>
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
															<input type="file" name="attachments[0]" class="form-control">
														</span>
                                                <a href="#" class="btn btn-default fileupload-exists" data-dismiss="fileupload">Remove</a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <form:hidden path="itemType"/>

                            </div>
                            <footer class="panel-footer text-center">
                                <button class="btn btn-primary">Submit</button>
                                <button type="reset" class="btn btn-default">Reset</button>
                            </footer>
                        </section>
                    </form:form>
                </div>
                <div class="col-md-2">
                </div>                
            </div>
            <!-- Display form for creating paid ad item End -->

        </c:when>        
        
    
		<c:when test="${itemBean.itemType == 'NEWS'}" >
		
            <!-- Display all news items start -->
            <div class="row">
                <div class="col-md-2">
                </div>
                <div class="col-md-8">
               	<section class="panel">
                    <header class="panel-heading">
                        <div class="panel-actions">
                            <a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
                            <a href="#" class="panel-action panel-action-dismiss" data-panel-dismiss></a>
                        </div>
                        <h2 class="panel-title text-center">News List</h2>
                    </header>
                    <div class="panel-body">
                        <table class="display table table-bordered table-striped" id="datatable-news">
                            <thead>
                            <tr>
                                <th width="60%" class="text-center">News Text</th>
                                <th width="10%" class="text-center">Valid From</th>
                                <th width="10%" class="text-center">Valid To</th>
                                <th width="10%" class="text-center">Date Created</th>
                                <th width="10%" class="text-center">Actions</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="itemVar" items="${items}">

                                <tr>

                                    <td>${itemVar.shortDescription}</td>
                                    <td><fmt:formatDate value="${itemVar.validFrom}" pattern="MMM-dd-yyyy"/></td>
                                    <td><fmt:formatDate value="${itemVar.validTo}" pattern="MMM-dd-yyyy"/></td>
                                    <td><fmt:formatDate value="${itemVar.dateCreated}" pattern="MMM-dd-yyyy"/></td>
                                    <td><a href="${pageContext.request.contextPath}/item/delete?id=${itemVar.id}&itemType=${itemVar.itemType}">Delete</a></td>

                                </tr>

                            </c:forEach>
                            </tbody>
                        </table>




                        <script type="text/javascript">

                            $(document).ready(function() {
                                $('#datatable-news').DataTable();
                            } );

                        </script>

                    </div>
                    </section>
                </div>
                <div class="col-md-2">
                </div>
            </div>
            <!-- Display all news items end -->

            <!-- Display form for creating news item -->
            <div class="row">
                <div class="col-md-2">
                </div>            
                <div class="col-md-8">

                    <form:form id="form2" action="${pageContext.request.contextPath}/item/create" cssClass="form-horizontal form-bordered" method="POST" commandName="itemBean"  enctype="multipart/form-data">
                        <section class="panel">
                            <header class="panel-heading">
                                <div class="panel-actions">
                                    <a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
                                    <a href="#" class="panel-action panel-action-dismiss" data-panel-dismiss></a>
                                </div>

                                <h2 class="panel-title text-center">Create News</h2>

                            </header>
                            <div class="panel-body">

                                <div class="form-group">
                                    <label class="col-sm-4 control-label" for="inputSuccess">News Text</label>
                                    <div class="col-sm-8">
                                        <form:textarea rows="4" cols="50" path="shortDescription" />
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
                                <form:hidden path="itemType"/>

                            </div>
                            <footer class="panel-footer text-center">
                                <button class="btn btn-primary">Submit</button>
                                <button type="reset" class="btn btn-default">Reset</button>
                            </footer>
                        </section>
                    </form:form>
                </div>
                <div class="col-md-2">
                </div>                
            </div>
            <!-- Display form for creating news item End -->		
		
		</c:when>
        <c:otherwise>
        </c:otherwise>
    </c:choose>




    <!-- end: page -->
</section>