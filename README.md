## Downloader Portlet - Liferay 7.x

It downloads any file (since it has permission) from server using portlet serveResource phase.

Commons-IO from apache is needed for this project. Since commons-io it is not OSGi readable, it is required to deploy the following common-io version on your server before deploying the portlet:

http://download.eclipse.org/tools/orbit/downloads/drops/R20170303204511/repository/plugins/org.apache.commons.io_2.2.0.v201405211200.jar

reference: https://download.eclipse.org/tools/orbit/downloads/drops/R20170919201930/

 