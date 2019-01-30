package org.eclipse.swt.internal.chromium;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;

public class BundleActivator implements org.osgi.framework.BundleActivator {

	private static BundleContext context;

	static BundleContext getContext() {
		return context;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.
	 * BundleContext)
	 */
	public void start(BundleContext bundleContext) throws Exception {
		BundleActivator.context = bundleContext;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext bundleContext) throws Exception {
		BundleActivator.context = null;
	}

	public static Enumeration<Bundle> getBundlesForCopyingLibraries() {
		List<Bundle> result = new ArrayList<Bundle>();
		addResourceUrl(result, "com.make.chromium.cef.win32.win32.x86_64");
		addResourceUrl(result, "org.eclipse.swt.chromium.win32.win32.x86_64");
		return Collections.enumeration(result);
	}
	
	private static Bundle getBundle(String symbolicName) {
		Bundle[] bundles = BundleActivator.context.getBundles();
		for (Bundle bundle : bundles) {
			if (symbolicName.equals(bundle.getSymbolicName())) {
				return bundle;
			}
		}
		return null;
	}

	private static void addResourceUrl(List<Bundle> collector, String bundleSymbolicName) {
		Bundle bundle = getBundle(bundleSymbolicName);
		if (bundle == null) {
			System.out.println("Error: bundle fragment not found: " + bundleSymbolicName);
		} else {
			collector.add(bundle);
		}

	}
}
