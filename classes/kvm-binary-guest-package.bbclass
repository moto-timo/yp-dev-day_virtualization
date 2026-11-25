SUMMARY = "Package for ${IMAGE_NAME}"
# Recipes must set LICENSE and LIC_FILES_CHKSUM for the image they package

INHIBIT_DEFAULT_DEPS = "1"

inherit bin_package

# Where to install the image
vesseldir ?= "${localstatedir}/lib/libvirt/images"

do_install[depends] += "libvirt:do_install"

S = "${UNPACKDIR}"

do_install () {
	install -d ${D}${vesseldir}
	install -m 0644 ${UNPACKDIR}/${VESSEL_PAYLOAD_NAME} ${D}${vesseldir}/${VESSEL_PAYLOAD_NAME}
}
