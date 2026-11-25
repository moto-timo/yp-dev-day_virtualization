SUMMARY = "Package for ${IMAGE_NAME}"
# The image is an aggregate; its license manifest is installed inside it
LICENSE = "LicenseRef-OE-Guest-Image"
LIC_FILES_CHKSUM = "file://${YPDD_LICENSE_DIR}/LicenseRef-OE-Guest-Image;md5=8232aaa0c00ee1e56f2160da404edaee"

PACKAGE_ARCH = "${MACHINE_ARCH}"
PACKAGES = "${PN}"

INHIBIT_DEFAULT_DEPS = "1"

# Variables to control where images are found: the multiconfig name, and the deploy dir.
VESSEL_PACKAGE_MC ?= ""
VESSEL_PACKAGE_DEPLOY_DIR ?= "${DEPLOY_DIR_IMAGE}"

# The name of the image
IMAGE_NAME := "${@d.getVar('PN').replace('-package', '')}"
IMAGE_NAME_SUFFIX ?= ".rootfs"
# Where to install the image
vesseldir ?= "${localstatedir}/lib/machines"

do_install[depends] += "virtual/kernel:do_deploy"
do_install[mcdepends] += "multiconfig::${VESSEL_PACKAGE_MC}:${IMAGE_NAME}:do_image_complete"

do_install () {
	install -d ${D}${vesseldir}
	install ${VESSEL_PACKAGE_DEPLOY_DIR}/Image ${D}${vesseldir}/Image
	install ${VESSEL_PACKAGE_DEPLOY_DIR}/${IMAGE_NAME}-${MACHINE}${IMAGE_NAME_SUFFIX}.wic.qcow2 ${D}${vesseldir}/${IMAGE_NAME}.wic.qcow2
}
