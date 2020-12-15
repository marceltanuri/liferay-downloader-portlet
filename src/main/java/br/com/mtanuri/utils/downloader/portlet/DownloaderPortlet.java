package br.com.mtanuri.utils.downloader.portlet;

import com.liferay.portal.kernel.io.ByteArrayFileInputStream;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.activation.MimetypesFileTypeMap;
import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.osgi.service.component.annotations.Component;

import br.com.mtanuri.utils.downloader.constants.DownloaderPortletKeys;

/**
 * @author marceltanuri
 */
@Component(immediate = true, property = { "com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.instanceable=true", "javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp", "javax.portlet.name=" + DownloaderPortletKeys.Downloader,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user" }, service = Portlet.class)
public class DownloaderPortlet extends MVCPortlet {

	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		super.doView(renderRequest, renderResponse);
	}

	@Override
	public void serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws IOException, PortletException {

		String path = ParamUtil.getString(resourceRequest, "path");

		log.info("downloading file: " + path);

		File file = new File(path);

		HttpServletResponse res = PortalUtil.getHttpServletResponse(resourceResponse);
		res.setHeader("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"");//
		res.setHeader("Content-Transfer-Encoding", "binary");
		res.setContentType(new MimetypesFileTypeMap().getContentType(file));
		
		res.getOutputStream().write(IOUtils.toByteArray(new FileInputStream(file)));

		res.flushBuffer();

	}

	private Log log = LogFactoryUtil.getLog(DownloaderPortlet.class);

}